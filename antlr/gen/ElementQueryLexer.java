// Generated from /Users/yangshengbing/Documents/idea_java/example/springboottest/antlr/src/main/antlr4/ElementQueryLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ElementQueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOT=1, COMMA=2, LB=3, RB=4, ID=5, VALUE=6, VALUE_LIST=7, STRING=8, NUMBER=9, 
		WS=10, AND=11, OR=12, NOT=13, EQ=14, NEQ=15, LT=16, LTE=17, GT=18, GTE=19, 
		INSIDE=20, OUTSIDE=21, BETWEEN=22, WITHIN=23, WITHOUT=24, STARTWITH=25, 
		ENDINGWITH=26, CONTAINING=27, NOT_STARTWITH=28, NOT_ENDINGWITH=29, NOT_CONTAINING=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DOT", "COMMA", "LB", "RB", "ID", "VALUE", "VALUE_LIST", "STRING", "NUMBER", 
			"INT", "EXP", "WS", "AND", "OR", "NOT", "EQ", "NEQ", "LT", "LTE", "GT", 
			"GTE", "INSIDE", "OUTSIDE", "BETWEEN", "WITHIN", "WITHOUT", "STARTWITH", 
			"ENDINGWITH", "CONTAINING", "NOT_STARTWITH", "NOT_ENDINGWITH", "NOT_CONTAINING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "','", "'('", "')'", null, null, null, null, null, null, 
			null, null, null, "'eq'", "'neq'", "'lt'", "'lte'", "'gt'", "'gte'", 
			"'inside'", "'outside'", "'between'", "'within'", "'without'", "'startingWith'", 
			"'endingWith'", "'containing'", "'notStartingWith'", "'notEndingWith'", 
			"'notContaining'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOT", "COMMA", "LB", "RB", "ID", "VALUE", "VALUE_LIST", "STRING", 
			"NUMBER", "WS", "AND", "OR", "NOT", "EQ", "NEQ", "LT", "LTE", "GT", "GTE", 
			"INSIDE", "OUTSIDE", "BETWEEN", "WITHIN", "WITHOUT", "STARTWITH", "ENDINGWITH", 
			"CONTAINING", "NOT_STARTWITH", "NOT_ENDINGWITH", "NOT_CONTAINING"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ElementQueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ElementQueryLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u012c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3"+
		"\7\3\7\5\7U\n\7\3\b\3\b\3\b\7\bZ\n\b\f\b\16\b]\13\b\3\b\3\b\3\b\7\bb\n"+
		"\b\f\b\16\be\13\b\5\bg\n\b\3\t\3\t\7\tk\n\t\f\t\16\tn\13\t\3\t\3\t\3\n"+
		"\5\ns\n\n\3\n\3\n\3\n\6\nx\n\n\r\n\16\ny\5\n|\n\n\3\n\5\n\177\n\n\3\13"+
		"\3\13\3\13\7\13\u0084\n\13\f\13\16\13\u0087\13\13\5\13\u0089\n\13\3\f"+
		"\3\f\5\f\u008d\n\f\3\f\3\f\3\r\6\r\u0092\n\r\r\r\16\r\u0093\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3l\2\"\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27\2\31\f\33\r\35\16\37\17"+
		"!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36"+
		"?\37A \3\2\17\4\2C\\c|\7\2//\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--"+
		"//\5\2\13\f\17\17\"\"\4\2CCcc\4\2PPpp\4\2FFff\4\2QQqq\4\2TTtt\4\2VVvv"+
		"\2\u0137\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13"+
		"K\3\2\2\2\rT\3\2\2\2\17f\3\2\2\2\21h\3\2\2\2\23r\3\2\2\2\25\u0088\3\2"+
		"\2\2\27\u008a\3\2\2\2\31\u0091\3\2\2\2\33\u0097\3\2\2\2\35\u009b\3\2\2"+
		"\2\37\u009e\3\2\2\2!\u00a2\3\2\2\2#\u00a5\3\2\2\2%\u00a9\3\2\2\2\'\u00ac"+
		"\3\2\2\2)\u00b0\3\2\2\2+\u00b3\3\2\2\2-\u00b7\3\2\2\2/\u00be\3\2\2\2\61"+
		"\u00c6\3\2\2\2\63\u00ce\3\2\2\2\65\u00d5\3\2\2\2\67\u00dd\3\2\2\29\u00ea"+
		"\3\2\2\2;\u00f5\3\2\2\2=\u0100\3\2\2\2?\u0110\3\2\2\2A\u011e\3\2\2\2C"+
		"D\7\60\2\2D\4\3\2\2\2EF\7.\2\2F\6\3\2\2\2GH\7*\2\2H\b\3\2\2\2IJ\7+\2\2"+
		"J\n\3\2\2\2KO\t\2\2\2LN\t\3\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2"+
		"\2P\f\3\2\2\2QO\3\2\2\2RU\5\21\t\2SU\5\23\n\2TR\3\2\2\2TS\3\2\2\2U\16"+
		"\3\2\2\2V[\5\21\t\2WX\7.\2\2XZ\5\21\t\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2"+
		"[\\\3\2\2\2\\g\3\2\2\2][\3\2\2\2^c\5\23\n\2_`\7.\2\2`b\5\23\n\2a_\3\2"+
		"\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2ec\3\2\2\2fV\3\2\2\2f^\3\2"+
		"\2\2g\20\3\2\2\2hl\7)\2\2ik\13\2\2\2ji\3\2\2\2kn\3\2\2\2lm\3\2\2\2lj\3"+
		"\2\2\2mo\3\2\2\2nl\3\2\2\2op\7)\2\2p\22\3\2\2\2qs\7/\2\2rq\3\2\2\2rs\3"+
		"\2\2\2st\3\2\2\2t{\5\25\13\2uw\7\60\2\2vx\t\4\2\2wv\3\2\2\2xy\3\2\2\2"+
		"yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{u\3\2\2\2{|\3\2\2\2|~\3\2\2\2}\177\5\27"+
		"\f\2~}\3\2\2\2~\177\3\2\2\2\177\24\3\2\2\2\u0080\u0089\7\62\2\2\u0081"+
		"\u0085\t\5\2\2\u0082\u0084\t\4\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2\2\2\u0089\26\3\2\2"+
		"\2\u008a\u008c\t\6\2\2\u008b\u008d\t\7\2\2\u008c\u008b\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\5\25\13\2\u008f\30\3\2\2\2\u0090"+
		"\u0092\t\b\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\r\2\2\u0096"+
		"\32\3\2\2\2\u0097\u0098\t\t\2\2\u0098\u0099\t\n\2\2\u0099\u009a\t\13\2"+
		"\2\u009a\34\3\2\2\2\u009b\u009c\t\f\2\2\u009c\u009d\t\r\2\2\u009d\36\3"+
		"\2\2\2\u009e\u009f\t\n\2\2\u009f\u00a0\t\f\2\2\u00a0\u00a1\t\16\2\2\u00a1"+
		" \3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7s\2\2\u00a4\"\3\2\2\2\u00a5"+
		"\u00a6\7p\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7s\2\2\u00a8$\3\2\2\2\u00a9"+
		"\u00aa\7n\2\2\u00aa\u00ab\7v\2\2\u00ab&\3\2\2\2\u00ac\u00ad\7n\2\2\u00ad"+
		"\u00ae\7v\2\2\u00ae\u00af\7g\2\2\u00af(\3\2\2\2\u00b0\u00b1\7i\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2*\3\2\2\2\u00b3\u00b4\7i\2\2\u00b4\u00b5\7v\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6,\3\2\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7p\2\2\u00b9"+
		"\u00ba\7u\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7f\2\2\u00bc\u00bd\7g\2\2"+
		"\u00bd.\3\2\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7w\2\2\u00c0\u00c1\7v\2"+
		"\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5"+
		"\7g\2\2\u00c5\60\3\2\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9"+
		"\7v\2\2\u00c9\u00ca\7y\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7g\2\2\u00cc"+
		"\u00cd\7p\2\2\u00cd\62\3\2\2\2\u00ce\u00cf\7y\2\2\u00cf\u00d0\7k\2\2\u00d0"+
		"\u00d1\7v\2\2\u00d1\u00d2\7j\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7p\2\2"+
		"\u00d4\64\3\2\2\2\u00d5\u00d6\7y\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7"+
		"v\2\2\u00d8\u00d9\7j\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7w\2\2\u00db\u00dc"+
		"\7v\2\2\u00dc\66\3\2\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7v\2\2\u00df\u00e0"+
		"\7c\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7k\2\2\u00e3"+
		"\u00e4\7p\2\2\u00e4\u00e5\7i\2\2\u00e5\u00e6\7Y\2\2\u00e6\u00e7\7k\2\2"+
		"\u00e7\u00e8\7v\2\2\u00e8\u00e9\7j\2\2\u00e98\3\2\2\2\u00ea\u00eb\7g\2"+
		"\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7f\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef"+
		"\7p\2\2\u00ef\u00f0\7i\2\2\u00f0\u00f1\7Y\2\2\u00f1\u00f2\7k\2\2\u00f2"+
		"\u00f3\7v\2\2\u00f3\u00f4\7j\2\2\u00f4:\3\2\2\2\u00f5\u00f6\7e\2\2\u00f6"+
		"\u00f7\7q\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7c\2\2"+
		"\u00fa\u00fb\7k\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe"+
		"\7p\2\2\u00fe\u00ff\7i\2\2\u00ff<\3\2\2\2\u0100\u0101\7p\2\2\u0101\u0102"+
		"\7q\2\2\u0102\u0103\7v\2\2\u0103\u0104\7U\2\2\u0104\u0105\7v\2\2\u0105"+
		"\u0106\7c\2\2\u0106\u0107\7t\2\2\u0107\u0108\7v\2\2\u0108\u0109\7k\2\2"+
		"\u0109\u010a\7p\2\2\u010a\u010b\7i\2\2\u010b\u010c\7Y\2\2\u010c\u010d"+
		"\7k\2\2\u010d\u010e\7v\2\2\u010e\u010f\7j\2\2\u010f>\3\2\2\2\u0110\u0111"+
		"\7p\2\2\u0111\u0112\7q\2\2\u0112\u0113\7v\2\2\u0113\u0114\7G\2\2\u0114"+
		"\u0115\7p\2\2\u0115\u0116\7f\2\2\u0116\u0117\7k\2\2\u0117\u0118\7p\2\2"+
		"\u0118\u0119\7i\2\2\u0119\u011a\7Y\2\2\u011a\u011b\7k\2\2\u011b\u011c"+
		"\7v\2\2\u011c\u011d\7j\2\2\u011d@\3\2\2\2\u011e\u011f\7p\2\2\u011f\u0120"+
		"\7q\2\2\u0120\u0121\7v\2\2\u0121\u0122\7E\2\2\u0122\u0123\7q\2\2\u0123"+
		"\u0124\7p\2\2\u0124\u0125\7v\2\2\u0125\u0126\7c\2\2\u0126\u0127\7k\2\2"+
		"\u0127\u0128\7p\2\2\u0128\u0129\7k\2\2\u0129\u012a\7p\2\2\u012a\u012b"+
		"\7i\2\2\u012bB\3\2\2\2\22\2MOT[cflry{~\u0085\u0088\u008c\u0093\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}