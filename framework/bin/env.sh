#!/usr/bin/env bash

readonly HOME_DIR="$(cd $(dirname $(dirname $0)); pwd)"
readonly BIN_DIR=${HOME_DIR}/bin
readonly LIB_DIR=${HOME_DIR}/lib
readonly CONF_DIR=${HOME_DIR}/conf
readonly LOG_DIR=${HOME_DIR}/log
readonly SW_DIR=${HOME_DIR}/agent

readonly SUPERVISOR="${BIN_DIR}/supervisor_sdn_topo_service"

readonly JDK_VERSION="jdk1.8.0"
readonly JDK_DIR="$( cd ~ && pwd)"
readonly DOWNLOAD_JDK=true
readonly DOWNLOAD_JDK_DIR="${JDK_DIR}/jdk_temp"
readonly DOWNLOAD_JDK_PKG_NAME="jdk-8u152-linux-x64.tar.gz"
readonly SKYWALKING_AGENT="${SW_DIR}/skywalking-agent.jar"

if [ -f ${BIN_DIR}/jvm.options ] ; then
    source ${BIN_DIR}/jvm.options
fi

# readonly PKG_NAME=topo-service-private-cloud.jar
readonly PKG_NAME="@project.artifactId@.jar"
readonly START_CMD="java ${JVM_OPTS} -javaagent:${SKYWALKING_AGENT} -jar ${LIB_DIR}/${PKG_NAME} \
--spring.profiles.active=prod \
--spring.cloud.bootstrap.location=file:${CONF_DIR}/bootstrap.yml \
--spring.config.location=file:${CONF_DIR}/application.yml"

readonly HEALTH_KEY_WORDS=${LIB_DIR}/${PKG_NAME}

function install_internal() {

    # 创建目录
    rm -rf ${LOG_DIR}
    mkdir -p ${LOG_DIR}

}
