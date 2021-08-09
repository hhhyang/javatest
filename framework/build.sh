#!/usr/bin/env bash

# set -x # Print trace
set -u # Variables can only be used after set.
set -e # Non-zero exit status must be captured.
set -o pipefail # Return exit status of the last successful command in a pipeline.

# paths

function info() {
    datetime=`date "+%Y-%m-%d %H:%M:%S |"`
    echo -e "\033[1;94m${datetime} INFO |\033[0m\033[0;94m $@ \033[0m"
}

function error() {
    datetime=`date "+%Y-%m-%d %H:%M:%S |"`
    echo -e "\033[1;91m${datetime} ERROR |\033[0m\033[1;91m $@ \033[0m" >&2
}

readonly BUILD_ROOT_DIR="$( cd $(dirname $0) && pwd)"
readonly OUTPUT_DIR="${BUILD_ROOT_DIR}/output"
readonly OUTPUT_TEMP_DIR="${BUILD_ROOT_DIR}/output_temp"

readonly JDK_VERSION="jdk1.8.0"
readonly JDK_DIR="$( cd ~ && pwd)"
readonly DOWNLOAD_JDK=true
readonly DOWNLOAD_JDK_DIR="${JDK_DIR}/jdk_temp"
readonly DOWNLOAD_JDK_PKG_NAME="jdk-8u152-linux-x64.tar.gz"

readonly MAVEN_NAME="apache-maven-3.5.0"
readonly MAVEN_DIR="$( cd ~ && pwd)"
readonly DOWNLOAD_MAVEN=true
readonly DOWNLOAD_MAVEN_DIR="${MAVEN_DIR}/jdk_temp"
readonly DOWNLOAD_MAVEN_PKG_NAME="apache-maven-3.5.0-bin.tar.gz"


function find_jdk() {

    target_jdk=""

    max_build_version=0
    for file in $(ls ${JDK_DIR})
    do
        if [ -d $(readlink ${file}) ] && [[ ${file} == ${JDK_VERSION}* ]] ; then
            build_version=${file##${JDK_VERSION}_}
            if [ "${build_version}" -gt "${max_build_version}" ] ; then
                target_jdk=${file}
                max_build_version=${build_version}
            fi
        fi
    done

    return 0
}

function calc_cpus() {

    case "`uname`" in
        Linux)
            cpu_cores=`egrep -c 'processor([[:space:]]+):.*' /proc/cpuinfo`
        ;;
        FreeBSD)
            cpu_cores=`sysctl hw.ncpu | awk '{print $2}'`
        ;;
        SunOS)
            cpu_cores=`psrinfo | wc -l`
        ;;
        Darwin)
            cpu_cores=`sysctl hw.ncpu | awk '{print $2}'`
        ;;
        *)
            cpu_cores="2"
        ;;
    esac

    if [ "$cpu_cores" -lt "1" ] ; then
        cpu_cores="1"
    fi

    if [ "$cpu_cores" -lt "3" ] ; then
        cpu_cores="1"
    elif [ "$cpu_cores" -gt "8" ] ; then
        cpu_cores=$(($cpu_cores - 4))
    else
        cpu_cores=$(($cpu_cores - 2))
    fi

}

function prepare_jdk_env() {
    info "check jdk environment......"

    JAVA_VERSION=$(java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }')
    if [[ ${JAVA_VERSION} == 1.8.0* ]] ; then
        info "check JDK version successfully, jdk version: ${JAVA_VERSION}"
        return 0
    fi

    find_jdk

    if [ -z "${target_jdk}" ] ; then
        if [ x"${DOWNLOAD_JDK}" = x"true" ]; then
            info "downloading jdk package from repository......"

            rm -rf ${DOWNLOAD_JDK_DIR}
            mkdir -p ${DOWNLOAD_JDK_DIR}
            curr_pwd=`pwd`
            cd ${DOWNLOAD_JDK_DIR}
            # Get Thirdparty Library (JDK1.8, Maven3.5.0 and Custom settings.xml)
            # wget
            tar -xzf output.tar.gz
            tar -xzf ${DOWNLOAD_JDK_DIR}/output/${DOWNLOAD_JDK_PKG_NAME} -C ${JDK_DIR}
            rm -rf ${DOWNLOAD_JDK_DIR}

            cd ${curr_pwd}
            find_jdk
        fi

        if [ -z "${target_jdk}" ] ; then
            error "check environment failed, do not exist required ${JDK_VERSION} in dedicated directory ${JDK_DIR}"
            return 1
        fi
    fi

    info "setup JDK 1.8.0 environment......"
    export JAVA_HOME=${JDK_DIR}/${target_jdk}
    export JRE_HOME=${JAVA_HOME}/jre
    export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
    export PATH=${JAVA_HOME}/bin:${PATH}

    return 0
}

function prepare_maven_env() {

    # Check Maven Version
    info "check maven environment......"

    # 下面这条语句，在某些情况下版本号不是出现在结果的第一行，判断会出错，只能用别的方法
    # MAVEN_VERSION=$(mvn -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }')
    # 在编译机上，执行当前脚本的到下面这条语句，如果grep的结果为空，会闪退；但是在编译机上单独执行下面这条语句，又能正常执行；why？
    # MAVEN_VERSION=$(mvn -version 2>&1 | grep 'Apache Maven 3.5')
    MAVEN_VERSION=$(mvn -version 2>&1)
    if [[ ${MAVEN_VERSION} == *'Apache Maven 3.5'* ]] ; then
        info "check Maven version successfully, maven version: ${MAVEN_VERSION}"
    elif [ x"${DOWNLOAD_MAVEN}" = x"true" ]; then
        info "downloading maven from repository......"

        rm -rf ${DOWNLOAD_MAVEN_DIR}
        mkdir -p ${DOWNLOAD_MAVEN_DIR}
        curr_pwd=`pwd`
        cd ${DOWNLOAD_MAVEN_DIR}

        # wget
        tar -xzf output.tar.gz
        tar -xzf ${DOWNLOAD_MAVEN_DIR}/output/${DOWNLOAD_MAVEN_PKG_NAME} -C ${MAVEN_DIR}
        rm -rf ${DOWNLOAD_MAVEN_DIR}

        cd ${curr_pwd}

        # Setup Maven environment.
        info "Setup Maven environment......"
        export M2_HOME=${MAVEN_DIR}/${MAVEN_NAME}
        export PATH=${M2_HOME}/bin:$PATH
    fi

    return 0
}

# 配置java环境以及依赖
function env_init() {
    info "prepare environment......"

    mkdir -p ${OUTPUT_TEMP_DIR}

    prepare_jdk_env

    prepare_maven_env

}

# 编译生成可执行文件
function build() {

    info "start build ......"

    calc_cpus

    mvn clean install -Dcheckstyle.skip=true -DskipTests -P${1} -T${cpu_cores}

    if [ $? -ne 0 ]; then
        error "build failed"
        exit 1
    fi
    info "build successfully"
}

function pack() {
    info "start packing......"

    rm -rf ${OUTPUT_DIR}
    mkdir -p ${OUTPUT_DIR}

    cp -rf ${BUILD_ROOT_DIR}/topo-service/${1}/target/output/sdn-topo-*/* ${OUTPUT_DIR}/

    if [ x"${1}" = x"abc" ] ; then
        info "set package version......"
        branch=$(sed -n '/git.branch=/s///p' ${BUILD_ROOT_DIR}/topo-service/${1}/target/classes/git.properties)
        if [[ ${branch} = *release_* ]] ; then
            version=${branch#release_}
            version=${version//-/.}
        else
            version="3.0.X"
        fi

        # sed -i 命令在macos上会报错，sed -ig在macos和centos上都能成功执行，但是会新建一个带后缀g的的文件，
        # 所以替换完后删除新生成的文件
        sed -ig "s/==VERSION==/${version}/" ${OUTPUT_DIR}/conf/bootstrap.yml.template
        rm -f ${OUTPUT_DIR}/conf/bootstrap.yml.templateg

        # 打包skywalking agent
        if [ ! -f "apache-skywalking-apm-es7-8.4.0.tar.gz" ]; then
            wget -q http://10.131.21.19:8123/apache-skywalking-apm-es7-8.4.0.tar.gz
        fi
        tar -xzf apache-skywalking-apm-es7-8.4.0.tar.gz
        mv apache-skywalking-apm-bin-es7/agent/ ${OUTPUT_DIR}
        rm -f ${OUTPUT_DIR}/agent/config/agent.config
        mv -f ${OUTPUT_DIR}/conf/skywalking-agent.config.template ${OUTPUT_DIR}/agent/config/agent.config.template
        rm -rf apache-skywalking-apm-es7-8.4.0.tar.gz apache-skywalking-apm-bin-es7
    fi

    if [ x"${1}" = x"cloud" ] ; then
        # BCLOUD 下载依赖
        bcloud local -U
        # 调用模板脚本，执行子模块的追加打包
        sh ../../industry-cloud/templates/tools/pack_charm_sub_module.sh
    fi
}

# 清理临时文件
function clean() {
    info "start clean......"

    rm -rf ${OUTPUT_TEMP_DIR}
}

function help_info() {
    info "usage: ${0} [default | abc | cloud]"
}

function main() {

    if [ $# -eq 0 ] ; then
        env_init
        build default
        pack abc
        clean
    elif [ x"${1}" = x"default" ] ; then
        env_init
        build default
        pack abc
        clean
    elif [ $# -eq 1 ] ; then
        env_init
        build topo-service-${1}
        pack $@
        clean
    else
        help_info
        exit 1
    fi

    info "Build done successfully"
}

main "$@"