// Generated from d:/Program Files (x86)/Git Projects/P4_Project/src/main/antlr4/p4project/OurGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class OurGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, NEGATIVE=38, 
		INT=39, FLOAT=40, BOOL=41, CHAR=42, STRING=43, THREAD=44, PREFIX=45, TYPE=46, 
		ID=47, LINE_COMMENT=48, BLOCK_COMMENT=49, WS=50;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_statementPrime = 2, RULE_assignment = 3, 
		RULE_assVar = 4, RULE_assFunc = 5, RULE_reassignment = 6, RULE_declaration = 7, 
		RULE_threadAssignment = 8, RULE_awaitStatement = 9, RULE_castExpression = 10, 
		RULE_typeRef = 11, RULE_ifStatement = 12, RULE_forStatement = 13, RULE_forVar = 14, 
		RULE_whileStatement = 15, RULE_continueStatement = 16, RULE_breakStatement = 17, 
		RULE_block = 18, RULE_returnStatement = 19, RULE_criticalSection = 20, 
		RULE_printStatement = 21, RULE_read = 22, RULE_expr = 23, RULE_equal = 24, 
		RULE_comp = 25, RULE_additive = 26, RULE_mult = 27, RULE_power = 28, RULE_arrayLiteral = 29, 
		RULE_arrayIndex = 30, RULE_factor = 31, RULE_functionCall = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "statementPrime", "assignment", "assVar", "assFunc", 
			"reassignment", "declaration", "threadAssignment", "awaitStatement", 
			"castExpression", "typeRef", "ifStatement", "forStatement", "forVar", 
			"whileStatement", "continueStatement", "breakStatement", "block", "returnStatement", 
			"criticalSection", "printStatement", "read", "expr", "equal", "comp", 
			"additive", "mult", "power", "arrayLiteral", "arrayIndex", "factor", 
			"functionCall"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "','", "')'", "'=>'", "'awaitAll'", "'awaitAny'", 
			"'cast'", "'['", "']'", "'if'", "'else if'", "'else'", "'for'", "'while'", 
			"'continue'", "'break'", "'{'", "'}'", "'return'", "'critical'", "'print'", 
			"'read'", "'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", 
			"'+'", "'*'", "'/'", "'%'", "'^'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NEGATIVE", "INT", "FLOAT", "BOOL", "CHAR", "STRING", "THREAD", 
			"PREFIX", "TYPE", "ID", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
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

	@Override
	public String getGrammarFileName() { return "OurGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public OurGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(OurGrammarParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290620322176L) != 0)) {
				{
				{
				setState(66);
				statement();
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementPrimeContext statementPrime() {
			return getRuleContext(StatementPrimeContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ThreadAssignmentContext threadAssignment() {
			return getRuleContext(ThreadAssignmentContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public CriticalSectionContext criticalSection() {
			return getRuleContext(CriticalSectionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				statementPrime();
				setState(75);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				threadAssignment();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				ifStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				forStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				whileStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(82);
				criticalSection();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementPrimeContext extends ParserRuleContext {
		public ReassignmentContext reassignment() {
			return getRuleContext(ReassignmentContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public AwaitStatementContext awaitStatement() {
			return getRuleContext(AwaitStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public StatementPrimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementPrime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterStatementPrime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitStatementPrime(this);
		}
	}

	public final StatementPrimeContext statementPrime() throws RecognitionException {
		StatementPrimeContext _localctx = new StatementPrimeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statementPrime);
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				reassignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
				awaitStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(89);
				continueStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(90);
				breakStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(91);
				returnStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(92);
				printStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(93);
				functionCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public AssVarContext assVar() {
			return getRuleContext(AssVarContext.class,0);
		}
		public AssFuncContext assFunc() {
			return getRuleContext(AssFuncContext.class,0);
		}
		public TerminalNode PREFIX() { return getToken(OurGrammarParser.PREFIX, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREFIX) {
				{
				setState(96);
				match(PREFIX);
				}
			}

			setState(99);
			typeRef();
			setState(100);
			match(ID);
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(101);
				assVar();
				setState(102);
				match(T__0);
				}
				break;
			case T__2:
				{
				setState(104);
				assFunc();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssVarContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterAssVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitAssVar(this);
		}
	}

	public final AssVarContext assVar() throws RecognitionException {
		AssVarContext _localctx = new AssVarContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__1);
			setState(108);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssFuncContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(OurGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OurGrammarParser.ID, i);
		}
		public List<TerminalNode> PREFIX() { return getTokens(OurGrammarParser.PREFIX); }
		public TerminalNode PREFIX(int i) {
			return getToken(OurGrammarParser.PREFIX, i);
		}
		public AssFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterAssFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitAssFunc(this);
		}
	}

	public final AssFuncContext assFunc() throws RecognitionException {
		AssFuncContext _localctx = new AssFuncContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__2);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREFIX || _la==TYPE) {
				{
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PREFIX) {
					{
					setState(111);
					match(PREFIX);
					}
				}

				setState(114);
				typeRef();
				setState(115);
				match(ID);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(116);
					match(T__3);
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==PREFIX) {
						{
						setState(117);
						match(PREFIX);
						}
					}

					setState(120);
					typeRef();
					setState(121);
					match(ID);
					}
					}
					setState(127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(130);
			match(T__4);
			setState(131);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReassignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public ReassignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reassignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterReassignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitReassignment(this);
		}
	}

	public final ReassignmentContext reassignment() throws RecognitionException {
		ReassignmentContext _localctx = new ReassignmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reassignment);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(ID);
				setState(134);
				match(T__1);
				setState(135);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				arrayIndex();
				setState(137);
				match(T__1);
				setState(138);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public TerminalNode PREFIX() { return getToken(OurGrammarParser.PREFIX, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREFIX) {
				{
				setState(142);
				match(PREFIX);
				}
			}

			setState(145);
			typeRef();
			setState(146);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ThreadAssignmentContext extends ParserRuleContext {
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ThreadAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_threadAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterThreadAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitThreadAssignment(this);
		}
	}

	public final ThreadAssignmentContext threadAssignment() throws RecognitionException {
		ThreadAssignmentContext _localctx = new ThreadAssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_threadAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			typeRef();
			setState(149);
			match(ID);
			setState(150);
			match(T__5);
			setState(151);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AwaitStatementContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(OurGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OurGrammarParser.ID, i);
		}
		public AwaitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_awaitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterAwaitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitAwaitStatement(this);
		}
	}

	public final AwaitStatementContext awaitStatement() throws RecognitionException {
		AwaitStatementContext _localctx = new AwaitStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_awaitStatement);
		int _la;
		try {
			setState(175);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(T__6);
				setState(154);
				match(T__2);
				setState(155);
				match(ID);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(156);
					match(T__3);
					setState(157);
					match(ID);
					}
					}
					setState(162);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(163);
				match(T__4);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(T__7);
				setState(165);
				match(T__2);
				setState(166);
				match(ID);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(167);
					match(T__3);
					setState(168);
					match(ID);
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(OurGrammarParser.TYPE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitCastExpression(this);
		}
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_castExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__8);
			setState(178);
			match(T__2);
			setState(179);
			match(TYPE);
			setState(180);
			match(T__4);
			setState(181);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeRefContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(OurGrammarParser.TYPE, 0); }
		public List<TerminalNode> ID() { return getTokens(OurGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OurGrammarParser.ID, i);
		}
		public List<TerminalNode> INT() { return getTokens(OurGrammarParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(OurGrammarParser.INT, i);
		}
		public TypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitTypeRef(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(TYPE);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(184);
				match(T__9);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT || _la==ID) {
					{
					setState(185);
					_la = _input.LA(1);
					if ( !(_la==INT || _la==ID) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(188);
				match(T__10);
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__11);
			setState(195);
			match(T__2);
			setState(196);
			expr();
			setState(197);
			match(T__4);
			setState(198);
			statement();
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(199);
					match(T__12);
					setState(200);
					match(T__2);
					setState(201);
					expr();
					setState(202);
					match(T__4);
					setState(203);
					statement();
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(210);
				match(T__13);
				setState(211);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public ForVarContext forVar() {
			return getRuleContext(ForVarContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReassignmentContext reassignment() {
			return getRuleContext(ReassignmentContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__14);
			setState(215);
			match(T__2);
			setState(216);
			forVar();
			setState(217);
			match(T__0);
			setState(218);
			expr();
			setState(219);
			match(T__0);
			setState(220);
			reassignment();
			setState(221);
			match(T__4);
			setState(222);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForVarContext extends ParserRuleContext {
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public AssVarContext assVar() {
			return getRuleContext(AssVarContext.class,0);
		}
		public AssFuncContext assFunc() {
			return getRuleContext(AssFuncContext.class,0);
		}
		public TerminalNode PREFIX() { return getToken(OurGrammarParser.PREFIX, 0); }
		public ReassignmentContext reassignment() {
			return getRuleContext(ReassignmentContext.class,0);
		}
		public ForVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterForVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitForVar(this);
		}
	}

	public final ForVarContext forVar() throws RecognitionException {
		ForVarContext _localctx = new ForVarContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forVar);
		int _la;
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PREFIX) {
					{
					setState(224);
					match(PREFIX);
					}
				}

				setState(227);
				typeRef();
				setState(228);
				match(ID);
				setState(231);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(229);
					assVar();
					}
					break;
				case T__2:
					{
					setState(230);
					assFunc();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				reassignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__15);
			setState(238);
			match(T__2);
			setState(239);
			expr();
			setState(240);
			match(T__4);
			setState(241);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__18);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290620322176L) != 0)) {
				{
				{
				setState(248);
				statement();
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__20);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 175646999840264L) != 0)) {
				{
				setState(257);
				expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CriticalSectionContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(OurGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OurGrammarParser.ID, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CriticalSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criticalSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterCriticalSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitCriticalSection(this);
		}
	}

	public final CriticalSectionContext criticalSection() throws RecognitionException {
		CriticalSectionContext _localctx = new CriticalSectionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_criticalSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__21);
			setState(261);
			match(T__2);
			setState(262);
			match(ID);
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(263);
				match(T__3);
				setState(264);
				match(ID);
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(270);
			match(T__4);
			setState(271);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitPrintStatement(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__22);
			setState(274);
			match(T__2);
			setState(275);
			expr();
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(276);
				match(T__3);
				setState(277);
				expr();
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(283);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(OurGrammarParser.TYPE, 0); }
		public ReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitRead(this);
		}
	}

	public final ReadContext read() throws RecognitionException {
		ReadContext _localctx = new ReadContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_read);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(T__23);
			setState(286);
			match(T__2);
			setState(287);
			match(TYPE);
			setState(288);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public EqualContext equal() {
			return getRuleContext(EqualContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expr);
		int _la;
		try {
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				equal();
				setState(291);
				_la = _input.LA(1);
				if ( !(_la==T__24 || _la==T__25) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(292);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				equal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualContext extends ParserRuleContext {
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public EqualContext equal() {
			return getRuleContext(EqualContext.class,0);
		}
		public EqualContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitEqual(this);
		}
	}

	public final EqualContext equal() throws RecognitionException {
		EqualContext _localctx = new EqualContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_equal);
		int _la;
		try {
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				comp();
				setState(298);
				_la = _input.LA(1);
				if ( !(_la==T__26 || _la==T__27) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(299);
				equal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(301);
				comp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompContext extends ParserRuleContext {
		public AdditiveContext additive() {
			return getRuleContext(AdditiveContext.class,0);
		}
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitComp(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_comp);
		int _la;
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				additive();
				setState(305);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(306);
				comp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(308);
				additive();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveContext extends ParserRuleContext {
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public AdditiveContext additive() {
			return getRuleContext(AdditiveContext.class,0);
		}
		public TerminalNode NEGATIVE() { return getToken(OurGrammarParser.NEGATIVE, 0); }
		public AdditiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterAdditive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitAdditive(this);
		}
	}

	public final AdditiveContext additive() throws RecognitionException {
		AdditiveContext _localctx = new AdditiveContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_additive);
		int _la;
		try {
			setState(316);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				mult();
				setState(312);
				_la = _input.LA(1);
				if ( !(_la==T__32 || _la==NEGATIVE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(313);
				additive();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				mult();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultContext extends ParserRuleContext {
		public PowerContext power() {
			return getRuleContext(PowerContext.class,0);
		}
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public MultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitMult(this);
		}
	}

	public final MultContext mult() throws RecognitionException {
		MultContext _localctx = new MultContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_mult);
		int _la;
		try {
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				power();
				setState(319);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 120259084288L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(320);
				mult();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				power();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowerContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public PowerContext power() {
			return getRuleContext(PowerContext.class,0);
		}
		public PowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitPower(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_power);
		try {
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(325);
				factor();
				setState(326);
				match(T__36);
				setState(327);
				power();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				factor();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitArrayLiteral(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_arrayLiteral);
		int _la;
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(343); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(332);
					match(T__18);
					setState(333);
					expr();
					setState(338);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(334);
						match(T__3);
						setState(335);
						expr();
						}
						}
						setState(340);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(341);
					match(T__19);
					}
					}
					setState(345); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__18 );
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(351); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(347);
					match(T__9);
					setState(348);
					expr();
					setState(349);
					match(T__10);
					}
					}
					setState(353); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__9 );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayIndexContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterArrayIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitArrayIndex(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_arrayIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(ID);
			setState(362); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(358);
				match(T__9);
				setState(359);
				expr();
				setState(360);
				match(T__10);
				}
				}
				setState(364); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__9 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public TerminalNode NEGATIVE() { return getToken(OurGrammarParser.NEGATIVE, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public TerminalNode INT() { return getToken(OurGrammarParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(OurGrammarParser.FLOAT, 0); }
		public TerminalNode BOOL() { return getToken(OurGrammarParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(OurGrammarParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(OurGrammarParser.STRING, 0); }
		public TerminalNode THREAD() { return getToken(OurGrammarParser.THREAD, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public ReadContext read() {
			return getRuleContext(ReadContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_factor);
		try {
			setState(384);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				match(NEGATIVE);
				setState(367);
				factor();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
				functionCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(369);
				arrayIndex();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(370);
				arrayLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(371);
				match(ID);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(372);
				match(INT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(373);
				match(FLOAT);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(374);
				match(BOOL);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(375);
				match(CHAR);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(376);
				match(STRING);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(377);
				match(THREAD);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(378);
				match(T__2);
				setState(379);
				expr();
				setState(380);
				match(T__4);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(382);
				castExpression();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(383);
				read();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			match(ID);
			setState(387);
			match(T__2);
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 175646999840264L) != 0)) {
				{
				setState(388);
				expr();
				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(389);
					match(T__3);
					setState(390);
					expr();
					}
					}
					setState(395);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(398);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00012\u0191\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0005\u0000D\b\u0000"+
		"\n\u0000\f\u0000G\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001U\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002_\b\u0002\u0001\u0003\u0003\u0003b\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003j\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003"+
		"\u0005q\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005w\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005|\b\u0005"+
		"\n\u0005\f\u0005\u007f\t\u0005\u0003\u0005\u0081\b\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u008d\b\u0006\u0001\u0007\u0003"+
		"\u0007\u0090\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005"+
		"\t\u009f\b\t\n\t\f\t\u00a2\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0005\t\u00aa\b\t\n\t\f\t\u00ad\t\t\u0001\t\u0003\t\u00b0\b\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00bb\b\u000b\u0001\u000b\u0005\u000b\u00be\b"+
		"\u000b\n\u000b\f\u000b\u00c1\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00ce\b\f\n"+
		"\f\f\f\u00d1\t\f\u0001\f\u0001\f\u0003\f\u00d5\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0003\u000e\u00e2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00e8\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00ec\b"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u00fa\b\u0012\n\u0012\f\u0012\u00fd\t\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0103\b\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u010a\b\u0014"+
		"\n\u0014\f\u0014\u010d\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0117"+
		"\b\u0015\n\u0015\f\u0015\u011a\t\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0128\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u012f\b\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u0136\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u013d\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0144\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u014b\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0005\u001d\u0151\b\u001d\n\u001d\f\u001d\u0154"+
		"\t\u001d\u0001\u001d\u0001\u001d\u0004\u001d\u0158\b\u001d\u000b\u001d"+
		"\f\u001d\u0159\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0004\u001d"+
		"\u0160\b\u001d\u000b\u001d\f\u001d\u0161\u0003\u001d\u0164\b\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0004\u001e\u016b"+
		"\b\u001e\u000b\u001e\f\u001e\u016c\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0181\b\u001f\u0001 "+
		"\u0001 \u0001 \u0001 \u0001 \u0005 \u0188\b \n \f \u018b\t \u0003 \u018d"+
		"\b \u0001 \u0001 \u0001 \u0000\u0000!\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@\u0000"+
		"\u0006\u0002\u0000\'\'//\u0001\u0000\u0019\u001a\u0001\u0000\u001b\u001c"+
		"\u0001\u0000\u001d \u0002\u0000!!&&\u0001\u0000\"$\u01af\u0000E\u0001"+
		"\u0000\u0000\u0000\u0002T\u0001\u0000\u0000\u0000\u0004^\u0001\u0000\u0000"+
		"\u0000\u0006a\u0001\u0000\u0000\u0000\bk\u0001\u0000\u0000\u0000\nn\u0001"+
		"\u0000\u0000\u0000\f\u008c\u0001\u0000\u0000\u0000\u000e\u008f\u0001\u0000"+
		"\u0000\u0000\u0010\u0094\u0001\u0000\u0000\u0000\u0012\u00af\u0001\u0000"+
		"\u0000\u0000\u0014\u00b1\u0001\u0000\u0000\u0000\u0016\u00b7\u0001\u0000"+
		"\u0000\u0000\u0018\u00c2\u0001\u0000\u0000\u0000\u001a\u00d6\u0001\u0000"+
		"\u0000\u0000\u001c\u00eb\u0001\u0000\u0000\u0000\u001e\u00ed\u0001\u0000"+
		"\u0000\u0000 \u00f3\u0001\u0000\u0000\u0000\"\u00f5\u0001\u0000\u0000"+
		"\u0000$\u00f7\u0001\u0000\u0000\u0000&\u0100\u0001\u0000\u0000\u0000("+
		"\u0104\u0001\u0000\u0000\u0000*\u0111\u0001\u0000\u0000\u0000,\u011d\u0001"+
		"\u0000\u0000\u0000.\u0127\u0001\u0000\u0000\u00000\u012e\u0001\u0000\u0000"+
		"\u00002\u0135\u0001\u0000\u0000\u00004\u013c\u0001\u0000\u0000\u00006"+
		"\u0143\u0001\u0000\u0000\u00008\u014a\u0001\u0000\u0000\u0000:\u0163\u0001"+
		"\u0000\u0000\u0000<\u0165\u0001\u0000\u0000\u0000>\u0180\u0001\u0000\u0000"+
		"\u0000@\u0182\u0001\u0000\u0000\u0000BD\u0003\u0002\u0001\u0000CB\u0001"+
		"\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000FH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000"+
		"\u0000HI\u0005\u0000\u0000\u0001I\u0001\u0001\u0000\u0000\u0000JK\u0003"+
		"\u0004\u0002\u0000KL\u0005\u0001\u0000\u0000LU\u0001\u0000\u0000\u0000"+
		"MU\u0003\u0006\u0003\u0000NU\u0003\u0010\b\u0000OU\u0003\u0018\f\u0000"+
		"PU\u0003\u001a\r\u0000QU\u0003\u001e\u000f\u0000RU\u0003(\u0014\u0000"+
		"SU\u0003$\u0012\u0000TJ\u0001\u0000\u0000\u0000TM\u0001\u0000\u0000\u0000"+
		"TN\u0001\u0000\u0000\u0000TO\u0001\u0000\u0000\u0000TP\u0001\u0000\u0000"+
		"\u0000TQ\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TS\u0001\u0000"+
		"\u0000\u0000U\u0003\u0001\u0000\u0000\u0000V_\u0003\f\u0006\u0000W_\u0003"+
		"\u000e\u0007\u0000X_\u0003\u0012\t\u0000Y_\u0003 \u0010\u0000Z_\u0003"+
		"\"\u0011\u0000[_\u0003&\u0013\u0000\\_\u0003*\u0015\u0000]_\u0003@ \u0000"+
		"^V\u0001\u0000\u0000\u0000^W\u0001\u0000\u0000\u0000^X\u0001\u0000\u0000"+
		"\u0000^Y\u0001\u0000\u0000\u0000^Z\u0001\u0000\u0000\u0000^[\u0001\u0000"+
		"\u0000\u0000^\\\u0001\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_\u0005"+
		"\u0001\u0000\u0000\u0000`b\u0005-\u0000\u0000a`\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0003\u0016\u000b"+
		"\u0000di\u0005/\u0000\u0000ef\u0003\b\u0004\u0000fg\u0005\u0001\u0000"+
		"\u0000gj\u0001\u0000\u0000\u0000hj\u0003\n\u0005\u0000ie\u0001\u0000\u0000"+
		"\u0000ih\u0001\u0000\u0000\u0000j\u0007\u0001\u0000\u0000\u0000kl\u0005"+
		"\u0002\u0000\u0000lm\u0003.\u0017\u0000m\t\u0001\u0000\u0000\u0000n\u0080"+
		"\u0005\u0003\u0000\u0000oq\u0005-\u0000\u0000po\u0001\u0000\u0000\u0000"+
		"pq\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0003\u0016\u000b"+
		"\u0000s}\u0005/\u0000\u0000tv\u0005\u0004\u0000\u0000uw\u0005-\u0000\u0000"+
		"vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000"+
		"\u0000xy\u0003\u0016\u000b\u0000yz\u0005/\u0000\u0000z|\u0001\u0000\u0000"+
		"\u0000{t\u0001\u0000\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0081\u0001\u0000\u0000"+
		"\u0000\u007f}\u0001\u0000\u0000\u0000\u0080p\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u0005\u0005\u0000\u0000\u0083\u0084\u0003$\u0012\u0000\u0084\u000b"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0005/\u0000\u0000\u0086\u0087\u0005"+
		"\u0002\u0000\u0000\u0087\u008d\u0003.\u0017\u0000\u0088\u0089\u0003<\u001e"+
		"\u0000\u0089\u008a\u0005\u0002\u0000\u0000\u008a\u008b\u0003.\u0017\u0000"+
		"\u008b\u008d\u0001\u0000\u0000\u0000\u008c\u0085\u0001\u0000\u0000\u0000"+
		"\u008c\u0088\u0001\u0000\u0000\u0000\u008d\r\u0001\u0000\u0000\u0000\u008e"+
		"\u0090\u0005-\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0003\u0016\u000b\u0000\u0092\u0093\u0005/\u0000\u0000\u0093\u000f\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0003\u0016\u000b\u0000\u0095\u0096\u0005"+
		"/\u0000\u0000\u0096\u0097\u0005\u0006\u0000\u0000\u0097\u0098\u0003$\u0012"+
		"\u0000\u0098\u0011\u0001\u0000\u0000\u0000\u0099\u009a\u0005\u0007\u0000"+
		"\u0000\u009a\u009b\u0005\u0003\u0000\u0000\u009b\u00a0\u0005/\u0000\u0000"+
		"\u009c\u009d\u0005\u0004\u0000\u0000\u009d\u009f\u0005/\u0000\u0000\u009e"+
		"\u009c\u0001\u0000\u0000\u0000\u009f\u00a2\u0001\u0000\u0000\u0000\u00a0"+
		"\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a3"+
		"\u00b0\u0005\u0005\u0000\u0000\u00a4\u00a5\u0005\b\u0000\u0000\u00a5\u00a6"+
		"\u0005\u0003\u0000\u0000\u00a6\u00ab\u0005/\u0000\u0000\u00a7\u00a8\u0005"+
		"\u0004\u0000\u0000\u00a8\u00aa\u0005/\u0000\u0000\u00a9\u00a7\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b0\u0005\u0005"+
		"\u0000\u0000\u00af\u0099\u0001\u0000\u0000\u0000\u00af\u00a4\u0001\u0000"+
		"\u0000\u0000\u00b0\u0013\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\t\u0000"+
		"\u0000\u00b2\u00b3\u0005\u0003\u0000\u0000\u00b3\u00b4\u0005.\u0000\u0000"+
		"\u00b4\u00b5\u0005\u0005\u0000\u0000\u00b5\u00b6\u0003.\u0017\u0000\u00b6"+
		"\u0015\u0001\u0000\u0000\u0000\u00b7\u00bf\u0005.\u0000\u0000\u00b8\u00ba"+
		"\u0005\n\u0000\u0000\u00b9\u00bb\u0007\u0000\u0000\u0000\u00ba\u00b9\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001"+
		"\u0000\u0000\u0000\u00bc\u00be\u0005\u000b\u0000\u0000\u00bd\u00b8\u0001"+
		"\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u0017\u0001"+
		"\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005"+
		"\f\u0000\u0000\u00c3\u00c4\u0005\u0003\u0000\u0000\u00c4\u00c5\u0003."+
		"\u0017\u0000\u00c5\u00c6\u0005\u0005\u0000\u0000\u00c6\u00cf\u0003\u0002"+
		"\u0001\u0000\u00c7\u00c8\u0005\r\u0000\u0000\u00c8\u00c9\u0005\u0003\u0000"+
		"\u0000\u00c9\u00ca\u0003.\u0017\u0000\u00ca\u00cb\u0005\u0005\u0000\u0000"+
		"\u00cb\u00cc\u0003\u0002\u0001\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000"+
		"\u00cd\u00c7\u0001\u0000\u0000\u0000\u00ce\u00d1\u0001\u0000\u0000\u0000"+
		"\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d4\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0005\u000e\u0000\u0000\u00d3\u00d5\u0003\u0002\u0001\u0000"+
		"\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d5\u0019\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005\u000f\u0000\u0000"+
		"\u00d7\u00d8\u0005\u0003\u0000\u0000\u00d8\u00d9\u0003\u001c\u000e\u0000"+
		"\u00d9\u00da\u0005\u0001\u0000\u0000\u00da\u00db\u0003.\u0017\u0000\u00db"+
		"\u00dc\u0005\u0001\u0000\u0000\u00dc\u00dd\u0003\f\u0006\u0000\u00dd\u00de"+
		"\u0005\u0005\u0000\u0000\u00de\u00df\u0003\u0002\u0001\u0000\u00df\u001b"+
		"\u0001\u0000\u0000\u0000\u00e0\u00e2\u0005-\u0000\u0000\u00e1\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e4\u0003\u0016\u000b\u0000\u00e4\u00e7\u0005"+
		"/\u0000\u0000\u00e5\u00e8\u0003\b\u0004\u0000\u00e6\u00e8\u0003\n\u0005"+
		"\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e8\u00ec\u0001\u0000\u0000\u0000\u00e9\u00ec\u0003\f\u0006\u0000"+
		"\u00ea\u00ec\u0005/\u0000\u0000\u00eb\u00e1\u0001\u0000\u0000\u0000\u00eb"+
		"\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00ec"+
		"\u001d\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u0010\u0000\u0000\u00ee"+
		"\u00ef\u0005\u0003\u0000\u0000\u00ef\u00f0\u0003.\u0017\u0000\u00f0\u00f1"+
		"\u0005\u0005\u0000\u0000\u00f1\u00f2\u0003\u0002\u0001\u0000\u00f2\u001f"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\u0011\u0000\u0000\u00f4!\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0005\u0012\u0000\u0000\u00f6#\u0001\u0000"+
		"\u0000\u0000\u00f7\u00fb\u0005\u0013\u0000\u0000\u00f8\u00fa\u0003\u0002"+
		"\u0001\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00fa\u00fd\u0001\u0000"+
		"\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0005\u0014\u0000\u0000\u00ff%\u0001\u0000\u0000"+
		"\u0000\u0100\u0102\u0005\u0015\u0000\u0000\u0101\u0103\u0003.\u0017\u0000"+
		"\u0102\u0101\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000"+
		"\u0103\'\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0016\u0000\u0000\u0105"+
		"\u0106\u0005\u0003\u0000\u0000\u0106\u010b\u0005/\u0000\u0000\u0107\u0108"+
		"\u0005\u0004\u0000\u0000\u0108\u010a\u0005/\u0000\u0000\u0109\u0107\u0001"+
		"\u0000\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u010e\u0001"+
		"\u0000\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u010f\u0005"+
		"\u0005\u0000\u0000\u010f\u0110\u0003$\u0012\u0000\u0110)\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0005\u0017\u0000\u0000\u0112\u0113\u0005\u0003\u0000"+
		"\u0000\u0113\u0118\u0003.\u0017\u0000\u0114\u0115\u0005\u0004\u0000\u0000"+
		"\u0115\u0117\u0003.\u0017\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117"+
		"\u011a\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0118"+
		"\u0119\u0001\u0000\u0000\u0000\u0119\u011b\u0001\u0000\u0000\u0000\u011a"+
		"\u0118\u0001\u0000\u0000\u0000\u011b\u011c\u0005\u0005\u0000\u0000\u011c"+
		"+\u0001\u0000\u0000\u0000\u011d\u011e\u0005\u0018\u0000\u0000\u011e\u011f"+
		"\u0005\u0003\u0000\u0000\u011f\u0120\u0005.\u0000\u0000\u0120\u0121\u0005"+
		"\u0005\u0000\u0000\u0121-\u0001\u0000\u0000\u0000\u0122\u0123\u00030\u0018"+
		"\u0000\u0123\u0124\u0007\u0001\u0000\u0000\u0124\u0125\u0003.\u0017\u0000"+
		"\u0125\u0128\u0001\u0000\u0000\u0000\u0126\u0128\u00030\u0018\u0000\u0127"+
		"\u0122\u0001\u0000\u0000\u0000\u0127\u0126\u0001\u0000\u0000\u0000\u0128"+
		"/\u0001\u0000\u0000\u0000\u0129\u012a\u00032\u0019\u0000\u012a\u012b\u0007"+
		"\u0002\u0000\u0000\u012b\u012c\u00030\u0018\u0000\u012c\u012f\u0001\u0000"+
		"\u0000\u0000\u012d\u012f\u00032\u0019\u0000\u012e\u0129\u0001\u0000\u0000"+
		"\u0000\u012e\u012d\u0001\u0000\u0000\u0000\u012f1\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u00034\u001a\u0000\u0131\u0132\u0007\u0003\u0000\u0000\u0132"+
		"\u0133\u00032\u0019\u0000\u0133\u0136\u0001\u0000\u0000\u0000\u0134\u0136"+
		"\u00034\u001a\u0000\u0135\u0130\u0001\u0000\u0000\u0000\u0135\u0134\u0001"+
		"\u0000\u0000\u0000\u01363\u0001\u0000\u0000\u0000\u0137\u0138\u00036\u001b"+
		"\u0000\u0138\u0139\u0007\u0004\u0000\u0000\u0139\u013a\u00034\u001a\u0000"+
		"\u013a\u013d\u0001\u0000\u0000\u0000\u013b\u013d\u00036\u001b\u0000\u013c"+
		"\u0137\u0001\u0000\u0000\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013d"+
		"5\u0001\u0000\u0000\u0000\u013e\u013f\u00038\u001c\u0000\u013f\u0140\u0007"+
		"\u0005\u0000\u0000\u0140\u0141\u00036\u001b\u0000\u0141\u0144\u0001\u0000"+
		"\u0000\u0000\u0142\u0144\u00038\u001c\u0000\u0143\u013e\u0001\u0000\u0000"+
		"\u0000\u0143\u0142\u0001\u0000\u0000\u0000\u01447\u0001\u0000\u0000\u0000"+
		"\u0145\u0146\u0003>\u001f\u0000\u0146\u0147\u0005%\u0000\u0000\u0147\u0148"+
		"\u00038\u001c\u0000\u0148\u014b\u0001\u0000\u0000\u0000\u0149\u014b\u0003"+
		">\u001f\u0000\u014a\u0145\u0001\u0000\u0000\u0000\u014a\u0149\u0001\u0000"+
		"\u0000\u0000\u014b9\u0001\u0000\u0000\u0000\u014c\u014d\u0005\u0013\u0000"+
		"\u0000\u014d\u0152\u0003.\u0017\u0000\u014e\u014f\u0005\u0004\u0000\u0000"+
		"\u014f\u0151\u0003.\u0017\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151"+
		"\u0154\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000\u0000\u0000\u0152"+
		"\u0153\u0001\u0000\u0000\u0000\u0153\u0155\u0001\u0000\u0000\u0000\u0154"+
		"\u0152\u0001\u0000\u0000\u0000\u0155\u0156\u0005\u0014\u0000\u0000\u0156"+
		"\u0158\u0001\u0000\u0000\u0000\u0157\u014c\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000\u0000\u0000\u0159"+
		"\u015a\u0001\u0000\u0000\u0000\u015a\u0164\u0001\u0000\u0000\u0000\u015b"+
		"\u015c\u0005\n\u0000\u0000\u015c\u015d\u0003.\u0017\u0000\u015d\u015e"+
		"\u0005\u000b\u0000\u0000\u015e\u0160\u0001\u0000\u0000\u0000\u015f\u015b"+
		"\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u015f"+
		"\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162\u0164"+
		"\u0001\u0000\u0000\u0000\u0163\u0157\u0001\u0000\u0000\u0000\u0163\u015f"+
		"\u0001\u0000\u0000\u0000\u0164;\u0001\u0000\u0000\u0000\u0165\u016a\u0005"+
		"/\u0000\u0000\u0166\u0167\u0005\n\u0000\u0000\u0167\u0168\u0003.\u0017"+
		"\u0000\u0168\u0169\u0005\u000b\u0000\u0000\u0169\u016b\u0001\u0000\u0000"+
		"\u0000\u016a\u0166\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000"+
		"\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016c\u016d\u0001\u0000\u0000"+
		"\u0000\u016d=\u0001\u0000\u0000\u0000\u016e\u016f\u0005&\u0000\u0000\u016f"+
		"\u0181\u0003>\u001f\u0000\u0170\u0181\u0003@ \u0000\u0171\u0181\u0003"+
		"<\u001e\u0000\u0172\u0181\u0003:\u001d\u0000\u0173\u0181\u0005/\u0000"+
		"\u0000\u0174\u0181\u0005\'\u0000\u0000\u0175\u0181\u0005(\u0000\u0000"+
		"\u0176\u0181\u0005)\u0000\u0000\u0177\u0181\u0005*\u0000\u0000\u0178\u0181"+
		"\u0005+\u0000\u0000\u0179\u0181\u0005,\u0000\u0000\u017a\u017b\u0005\u0003"+
		"\u0000\u0000\u017b\u017c\u0003.\u0017\u0000\u017c\u017d\u0005\u0005\u0000"+
		"\u0000\u017d\u0181\u0001\u0000\u0000\u0000\u017e\u0181\u0003\u0014\n\u0000"+
		"\u017f\u0181\u0003,\u0016\u0000\u0180\u016e\u0001\u0000\u0000\u0000\u0180"+
		"\u0170\u0001\u0000\u0000\u0000\u0180\u0171\u0001\u0000\u0000\u0000\u0180"+
		"\u0172\u0001\u0000\u0000\u0000\u0180\u0173\u0001\u0000\u0000\u0000\u0180"+
		"\u0174\u0001\u0000\u0000\u0000\u0180\u0175\u0001\u0000\u0000\u0000\u0180"+
		"\u0176\u0001\u0000\u0000\u0000\u0180\u0177\u0001\u0000\u0000\u0000\u0180"+
		"\u0178\u0001\u0000\u0000\u0000\u0180\u0179\u0001\u0000\u0000\u0000\u0180"+
		"\u017a\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0180"+
		"\u017f\u0001\u0000\u0000\u0000\u0181?\u0001\u0000\u0000\u0000\u0182\u0183"+
		"\u0005/\u0000\u0000\u0183\u018c\u0005\u0003\u0000\u0000\u0184\u0189\u0003"+
		".\u0017\u0000\u0185\u0186\u0005\u0004\u0000\u0000\u0186\u0188\u0003.\u0017"+
		"\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u018b\u0001\u0000\u0000"+
		"\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000"+
		"\u0000\u018a\u018d\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u0184\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000\u0000"+
		"\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u018f\u0005\u0005\u0000"+
		"\u0000\u018fA\u0001\u0000\u0000\u0000\'ET^aipv}\u0080\u008c\u008f\u00a0"+
		"\u00ab\u00af\u00ba\u00bf\u00cf\u00d4\u00e1\u00e7\u00eb\u00fb\u0102\u010b"+
		"\u0118\u0127\u012e\u0135\u013c\u0143\u014a\u0152\u0159\u0161\u0163\u016c"+
		"\u0180\u0189\u018c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}