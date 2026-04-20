// Generated from OurGrammar.g4 by ANTLR 4.13.1

package p4project;

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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, INT=38, FLOAT=39, 
		BOOL=40, CHAR=41, STRING=42, THREAD=43, PREFIX=44, TYPE=45, ID=46, LINE_COMMENT=47, 
		BLOCK_COMMENT=48, WS=49;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_assignment = 2, RULE_reassignment = 3, 
		RULE_declaration = 4, RULE_threadAssignment = 5, RULE_awaitStatement = 6, 
		RULE_typeRef = 7, RULE_ifStatement = 8, RULE_forStatement = 9, RULE_whileStatement = 10, 
		RULE_continueStatement = 11, RULE_breakStatement = 12, RULE_block = 13, 
		RULE_returnStatement = 14, RULE_criticalSection = 15, RULE_printStatement = 16, 
		RULE_readStatement = 17, RULE_expr = 18, RULE_equal = 19, RULE_comp = 20, 
		RULE_additive = 21, RULE_mult = 22, RULE_power = 23, RULE_arrayLiteral = 24, 
		RULE_arrayIndex = 25, RULE_factor = 26, RULE_functionCall = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "assignment", "reassignment", "declaration", 
			"threadAssignment", "awaitStatement", "typeRef", "ifStatement", "forStatement", 
			"whileStatement", "continueStatement", "breakStatement", "block", "returnStatement", 
			"criticalSection", "printStatement", "readStatement", "expr", "equal", 
			"comp", "additive", "mult", "power", "arrayLiteral", "arrayIndex", "factor", 
			"functionCall"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "','", "')'", "'=>'", "'await'", "'awaitAny'", 
			"'['", "']'", "'if'", "'else if'", "'else'", "'for'", "'while'", "'continue'", 
			"'break'", "'{'", "'}'", "'return'", "'critical'", "'print'", "'read'", 
			"'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'^'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "INT", "FLOAT", "BOOL", "CHAR", "STRING", "THREAD", "PREFIX", 
			"TYPE", "ID", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
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
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140471216622472L) != 0)) {
				{
				{
				setState(56);
				statement();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ReassignmentContext reassignment() {
			return getRuleContext(ReassignmentContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ThreadAssignmentContext threadAssignment() {
			return getRuleContext(ThreadAssignmentContext.class,0);
		}
		public AwaitStatementContext awaitStatement() {
			return getRuleContext(AwaitStatementContext.class,0);
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
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public CriticalSectionContext criticalSection() {
			return getRuleContext(CriticalSectionContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
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
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				expr();
				setState(65);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				arrayIndex();
				setState(68);
				match(T__1);
				setState(69);
				expr();
				setState(70);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				assignment();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				reassignment();
				setState(74);
				match(T__0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				declaration();
				setState(77);
				match(T__0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				threadAssignment();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(80);
				awaitStatement();
				setState(81);
				match(T__0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				ifStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(84);
				forStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(85);
				whileStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(86);
				continueStatement();
				setState(87);
				match(T__0);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(89);
				breakStatement();
				setState(90);
				match(T__0);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(92);
				block();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(93);
				returnStatement();
				setState(94);
				match(T__0);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(96);
				criticalSection();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(97);
				printStatement();
				setState(98);
				match(T__0);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(100);
				readStatement();
				setState(101);
				match(T__0);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> PREFIX() { return getTokens(OurGrammarParser.PREFIX); }
		public TerminalNode PREFIX(int i) {
			return getToken(OurGrammarParser.PREFIX, i);
		}
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
		enterRule(_localctx, 4, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PREFIX) {
				{
				{
				setState(105);
				match(PREFIX);
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			typeRef();
			setState(112);
			match(ID);
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(113);
				match(T__1);
				setState(114);
				expr();
				setState(115);
				match(T__0);
				}
				break;
			case T__2:
				{
				setState(117);
				match(T__2);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PREFIX || _la==TYPE) {
					{
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==PREFIX) {
						{
						setState(118);
						match(PREFIX);
						}
					}

					setState(121);
					typeRef();
					setState(122);
					match(ID);
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(123);
						match(T__3);
						setState(125);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==PREFIX) {
							{
							setState(124);
							match(PREFIX);
							}
						}

						setState(127);
						typeRef();
						setState(128);
						match(ID);
						}
						}
						setState(134);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(137);
				match(T__4);
				setState(138);
				block();
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
	public static class ReassignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		enterRule(_localctx, 6, RULE_reassignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(ID);
			setState(142);
			match(T__1);
			setState(143);
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
	public static class DeclarationContext extends ParserRuleContext {
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public List<TerminalNode> PREFIX() { return getTokens(OurGrammarParser.PREFIX); }
		public TerminalNode PREFIX(int i) {
			return getToken(OurGrammarParser.PREFIX, i);
		}
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
		enterRule(_localctx, 8, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PREFIX) {
				{
				{
				setState(145);
				match(PREFIX);
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			typeRef();
			setState(152);
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
		enterRule(_localctx, 10, RULE_threadAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			typeRef();
			setState(155);
			match(ID);
			setState(156);
			match(T__5);
			setState(157);
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
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
		enterRule(_localctx, 12, RULE_awaitStatement);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(T__6);
				setState(160);
				match(T__2);
				setState(161);
				expr();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(162);
					match(T__3);
					setState(163);
					expr();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(169);
				match(T__4);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(T__7);
				setState(172);
				match(T__2);
				setState(173);
				expr();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(174);
					match(T__3);
					setState(175);
					expr();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
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
		enterRule(_localctx, 14, RULE_typeRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(TYPE);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(186);
				match(T__8);
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT || _la==ID) {
					{
					setState(187);
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

				setState(190);
				match(T__9);
				}
				}
				setState(195);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		enterRule(_localctx, 16, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__10);
			setState(197);
			match(T__2);
			setState(198);
			expr();
			setState(199);
			match(T__4);
			setState(200);
			statement();
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(201);
					match(T__11);
					setState(202);
					statement();
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(208);
				match(T__12);
				setState(209);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReassignmentContext reassignment() {
			return getRuleContext(ReassignmentContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
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
		enterRule(_localctx, 18, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__13);
			setState(213);
			match(T__2);
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PREFIX:
			case TYPE:
				{
				setState(214);
				assignment();
				}
				break;
			case ID:
				{
				setState(215);
				match(ID);
				setState(216);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(219);
			expr();
			setState(220);
			match(T__0);
			setState(221);
			reassignment();
			setState(222);
			match(T__4);
			setState(223);
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
		enterRule(_localctx, 20, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(T__14);
			setState(226);
			match(T__2);
			setState(227);
			expr();
			setState(228);
			match(T__4);
			setState(229);
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
		enterRule(_localctx, 22, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(T__15);
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
		enterRule(_localctx, 24, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
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
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__17);
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140471216622472L) != 0)) {
				{
				{
				setState(236);
				statement();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
			match(T__18);
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
		enterRule(_localctx, 28, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(T__19);
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 87694642250248L) != 0)) {
				{
				setState(245);
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
		enterRule(_localctx, 30, RULE_criticalSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(T__20);
			setState(249);
			match(T__2);
			setState(250);
			match(ID);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(251);
				match(T__3);
				setState(252);
				match(ID);
				}
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(258);
			match(T__4);
			setState(259);
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
		enterRule(_localctx, 32, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(T__21);
			setState(262);
			match(T__2);
			setState(263);
			expr();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(264);
				match(T__3);
				setState(265);
				expr();
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
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
	public static class ReadStatementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OurGrammarListener ) ((OurGrammarListener)listener).exitReadStatement(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_readStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__22);
			setState(274);
			match(T__2);
			setState(275);
			match(ID);
			setState(276);
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
		enterRule(_localctx, 36, RULE_expr);
		int _la;
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				equal();
				setState(279);
				_la = _input.LA(1);
				if ( !(_la==T__23 || _la==T__24) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(280);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
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
		enterRule(_localctx, 38, RULE_equal);
		int _la;
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				comp();
				setState(286);
				_la = _input.LA(1);
				if ( !(_la==T__25 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(287);
				equal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
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
		enterRule(_localctx, 40, RULE_comp);
		int _la;
		try {
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				additive();
				setState(293);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4026531840L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(294);
				comp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
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
		enterRule(_localctx, 42, RULE_additive);
		int _la;
		try {
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				mult();
				setState(300);
				_la = _input.LA(1);
				if ( !(_la==T__31 || _la==T__32) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(301);
				additive();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
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
		enterRule(_localctx, 44, RULE_mult);
		int _la;
		try {
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				power();
				setState(307);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 120259084288L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(308);
				mult();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
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
		enterRule(_localctx, 46, RULE_power);
		try {
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				factor();
				setState(314);
				match(T__36);
				setState(315);
				power();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317);
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
		enterRule(_localctx, 48, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(T__8);
			setState(321);
			expr();
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(322);
				match(T__3);
				setState(323);
				expr();
				}
				}
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(329);
			match(T__9);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		enterRule(_localctx, 50, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(ID);
			setState(332);
			match(T__8);
			setState(333);
			expr();
			setState(334);
			match(T__9);
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
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode ID() { return getToken(OurGrammarParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public TerminalNode INT() { return getToken(OurGrammarParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(OurGrammarParser.FLOAT, 0); }
		public TerminalNode BOOL() { return getToken(OurGrammarParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(OurGrammarParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(OurGrammarParser.STRING, 0); }
		public TerminalNode THREAD() { return getToken(OurGrammarParser.THREAD, 0); }
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
		enterRule(_localctx, 52, RULE_factor);
		int _la;
		try {
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(336);
				match(T__32);
				setState(337);
				factor();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				functionCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(ID);
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(340);
					match(T__8);
					setState(341);
					expr();
					setState(342);
					match(T__9);
					}
					}
					setState(348);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(349);
				arrayLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(350);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(351);
				match(FLOAT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(352);
				match(BOOL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(353);
				match(CHAR);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(354);
				match(STRING);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(355);
				match(THREAD);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(356);
				match(T__2);
				setState(357);
				expr();
				setState(358);
				match(T__4);
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
		enterRule(_localctx, 54, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(ID);
			setState(363);
			match(T__2);
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 87694642250248L) != 0)) {
				{
				setState(364);
				expr();
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(365);
					match(T__3);
					setState(366);
					expr();
					}
					}
					setState(371);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(374);
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
		"\u0004\u00011\u0179\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0001\u0000\u0005\u0000:\b\u0000\n\u0000\f\u0000=\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001h\b\u0001"+
		"\u0001\u0002\u0005\u0002k\b\u0002\n\u0002\f\u0002n\t\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002x\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002~\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002\u0083\b\u0002\n\u0002\f\u0002\u0086\t\u0002\u0003\u0002\u0088"+
		"\b\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u008c\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0005\u0004\u0093\b\u0004"+
		"\n\u0004\f\u0004\u0096\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a5\b\u0006\n"+
		"\u0006\f\u0006\u00a8\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00b1\b\u0006\n"+
		"\u0006\f\u0006\u00b4\t\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00b8"+
		"\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00bd\b\u0007"+
		"\u0001\u0007\u0005\u0007\u00c0\b\u0007\n\u0007\f\u0007\u00c3\t\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00cc\b\b\n"+
		"\b\f\b\u00cf\t\b\u0001\b\u0001\b\u0003\b\u00d3\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u00da\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0005\r\u00ee\b\r\n\r\f\r"+
		"\u00f1\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00f7\b"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00fe\b\u000f\n\u000f\f\u000f\u0101\t\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u010b\b\u0010\n\u0010\f\u0010\u010e\t\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u011c"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0123\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u012a\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u0131\b\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0138\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u013f\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0145\b\u0018\n"+
		"\u0018\f\u0018\u0148\t\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u0159\b\u001a\n\u001a\f\u001a\u015c\t\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0169\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u0170\b\u001b"+
		"\n\u001b\f\u001b\u0173\t\u001b\u0003\u001b\u0175\b\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0000\u0000\u001c\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246\u0000\u0006"+
		"\u0002\u0000&&..\u0001\u0000\u0018\u0019\u0001\u0000\u001a\u001b\u0001"+
		"\u0000\u001c\u001f\u0001\u0000 !\u0001\u0000\"$\u0194\u0000;\u0001\u0000"+
		"\u0000\u0000\u0002g\u0001\u0000\u0000\u0000\u0004l\u0001\u0000\u0000\u0000"+
		"\u0006\u008d\u0001\u0000\u0000\u0000\b\u0094\u0001\u0000\u0000\u0000\n"+
		"\u009a\u0001\u0000\u0000\u0000\f\u00b7\u0001\u0000\u0000\u0000\u000e\u00b9"+
		"\u0001\u0000\u0000\u0000\u0010\u00c4\u0001\u0000\u0000\u0000\u0012\u00d4"+
		"\u0001\u0000\u0000\u0000\u0014\u00e1\u0001\u0000\u0000\u0000\u0016\u00e7"+
		"\u0001\u0000\u0000\u0000\u0018\u00e9\u0001\u0000\u0000\u0000\u001a\u00eb"+
		"\u0001\u0000\u0000\u0000\u001c\u00f4\u0001\u0000\u0000\u0000\u001e\u00f8"+
		"\u0001\u0000\u0000\u0000 \u0105\u0001\u0000\u0000\u0000\"\u0111\u0001"+
		"\u0000\u0000\u0000$\u011b\u0001\u0000\u0000\u0000&\u0122\u0001\u0000\u0000"+
		"\u0000(\u0129\u0001\u0000\u0000\u0000*\u0130\u0001\u0000\u0000\u0000,"+
		"\u0137\u0001\u0000\u0000\u0000.\u013e\u0001\u0000\u0000\u00000\u0140\u0001"+
		"\u0000\u0000\u00002\u014b\u0001\u0000\u0000\u00004\u0168\u0001\u0000\u0000"+
		"\u00006\u016a\u0001\u0000\u0000\u00008:\u0003\u0002\u0001\u000098\u0001"+
		"\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000"+
		";<\u0001\u0000\u0000\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000"+
		"\u0000>?\u0005\u0000\u0000\u0001?\u0001\u0001\u0000\u0000\u0000@A\u0003"+
		"$\u0012\u0000AB\u0005\u0001\u0000\u0000Bh\u0001\u0000\u0000\u0000CD\u0003"+
		"2\u0019\u0000DE\u0005\u0002\u0000\u0000EF\u0003$\u0012\u0000FG\u0005\u0001"+
		"\u0000\u0000Gh\u0001\u0000\u0000\u0000Hh\u0003\u0004\u0002\u0000IJ\u0003"+
		"\u0006\u0003\u0000JK\u0005\u0001\u0000\u0000Kh\u0001\u0000\u0000\u0000"+
		"LM\u0003\b\u0004\u0000MN\u0005\u0001\u0000\u0000Nh\u0001\u0000\u0000\u0000"+
		"Oh\u0003\n\u0005\u0000PQ\u0003\f\u0006\u0000QR\u0005\u0001\u0000\u0000"+
		"Rh\u0001\u0000\u0000\u0000Sh\u0003\u0010\b\u0000Th\u0003\u0012\t\u0000"+
		"Uh\u0003\u0014\n\u0000VW\u0003\u0016\u000b\u0000WX\u0005\u0001\u0000\u0000"+
		"Xh\u0001\u0000\u0000\u0000YZ\u0003\u0018\f\u0000Z[\u0005\u0001\u0000\u0000"+
		"[h\u0001\u0000\u0000\u0000\\h\u0003\u001a\r\u0000]^\u0003\u001c\u000e"+
		"\u0000^_\u0005\u0001\u0000\u0000_h\u0001\u0000\u0000\u0000`h\u0003\u001e"+
		"\u000f\u0000ab\u0003 \u0010\u0000bc\u0005\u0001\u0000\u0000ch\u0001\u0000"+
		"\u0000\u0000de\u0003\"\u0011\u0000ef\u0005\u0001\u0000\u0000fh\u0001\u0000"+
		"\u0000\u0000g@\u0001\u0000\u0000\u0000gC\u0001\u0000\u0000\u0000gH\u0001"+
		"\u0000\u0000\u0000gI\u0001\u0000\u0000\u0000gL\u0001\u0000\u0000\u0000"+
		"gO\u0001\u0000\u0000\u0000gP\u0001\u0000\u0000\u0000gS\u0001\u0000\u0000"+
		"\u0000gT\u0001\u0000\u0000\u0000gU\u0001\u0000\u0000\u0000gV\u0001\u0000"+
		"\u0000\u0000gY\u0001\u0000\u0000\u0000g\\\u0001\u0000\u0000\u0000g]\u0001"+
		"\u0000\u0000\u0000g`\u0001\u0000\u0000\u0000ga\u0001\u0000\u0000\u0000"+
		"gd\u0001\u0000\u0000\u0000h\u0003\u0001\u0000\u0000\u0000ik\u0005,\u0000"+
		"\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000"+
		"\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000op\u0003\u000e\u0007\u0000p\u008b\u0005.\u0000\u0000"+
		"qr\u0005\u0002\u0000\u0000rs\u0003$\u0012\u0000st\u0005\u0001\u0000\u0000"+
		"t\u008c\u0001\u0000\u0000\u0000u\u0087\u0005\u0003\u0000\u0000vx\u0005"+
		",\u0000\u0000wv\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001"+
		"\u0000\u0000\u0000yz\u0003\u000e\u0007\u0000z\u0084\u0005.\u0000\u0000"+
		"{}\u0005\u0004\u0000\u0000|~\u0005,\u0000\u0000}|\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u0003\u000e\u0007\u0000\u0080\u0081\u0005.\u0000\u0000\u0081\u0083\u0001"+
		"\u0000\u0000\u0000\u0082{\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0087w\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0005\u0000"+
		"\u0000\u008a\u008c\u0003\u001a\r\u0000\u008bq\u0001\u0000\u0000\u0000"+
		"\u008bu\u0001\u0000\u0000\u0000\u008c\u0005\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0005.\u0000\u0000\u008e\u008f\u0005\u0002\u0000\u0000\u008f\u0090"+
		"\u0003$\u0012\u0000\u0090\u0007\u0001\u0000\u0000\u0000\u0091\u0093\u0005"+
		",\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000"+
		"\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000"+
		"\u0000\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0003\u000e\u0007\u0000\u0098\u0099\u0005.\u0000"+
		"\u0000\u0099\t\u0001\u0000\u0000\u0000\u009a\u009b\u0003\u000e\u0007\u0000"+
		"\u009b\u009c\u0005.\u0000\u0000\u009c\u009d\u0005\u0006\u0000\u0000\u009d"+
		"\u009e\u0003\u001a\r\u0000\u009e\u000b\u0001\u0000\u0000\u0000\u009f\u00a0"+
		"\u0005\u0007\u0000\u0000\u00a0\u00a1\u0005\u0003\u0000\u0000\u00a1\u00a6"+
		"\u0003$\u0012\u0000\u00a2\u00a3\u0005\u0004\u0000\u0000\u00a3\u00a5\u0003"+
		"$\u0012\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a9\u00aa\u0005\u0005\u0000\u0000\u00aa\u00b8\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0005\b\u0000\u0000\u00ac\u00ad\u0005\u0003\u0000"+
		"\u0000\u00ad\u00b2\u0003$\u0012\u0000\u00ae\u00af\u0005\u0004\u0000\u0000"+
		"\u00af\u00b1\u0003$\u0012\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005\u0005\u0000\u0000\u00b6"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b7\u009f\u0001\u0000\u0000\u0000\u00b7"+
		"\u00ab\u0001\u0000\u0000\u0000\u00b8\r\u0001\u0000\u0000\u0000\u00b9\u00c1"+
		"\u0005-\u0000\u0000\u00ba\u00bc\u0005\t\u0000\u0000\u00bb\u00bd\u0007"+
		"\u0000\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00c0\u0005"+
		"\n\u0000\u0000\u00bf\u00ba\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c2\u000f\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c5\u0005\u000b\u0000\u0000\u00c5\u00c6\u0005\u0003"+
		"\u0000\u0000\u00c6\u00c7\u0003$\u0012\u0000\u00c7\u00c8\u0005\u0005\u0000"+
		"\u0000\u00c8\u00cd\u0003\u0002\u0001\u0000\u00c9\u00ca\u0005\f\u0000\u0000"+
		"\u00ca\u00cc\u0003\u0002\u0001\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d2\u0001\u0000\u0000\u0000"+
		"\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\r\u0000\u0000\u00d1"+
		"\u00d3\u0003\u0002\u0001\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d3\u0011\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0005\u000e\u0000\u0000\u00d5\u00d9\u0005\u0003\u0000\u0000\u00d6"+
		"\u00da\u0003\u0004\u0002\u0000\u00d7\u00d8\u0005.\u0000\u0000\u00d8\u00da"+
		"\u0005\u0001\u0000\u0000\u00d9\u00d6\u0001\u0000\u0000\u0000\u00d9\u00d7"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0003$\u0012\u0000\u00dc\u00dd\u0005\u0001\u0000\u0000\u00dd\u00de\u0003"+
		"\u0006\u0003\u0000\u00de\u00df\u0005\u0005\u0000\u0000\u00df\u00e0\u0003"+
		"\u0002\u0001\u0000\u00e0\u0013\u0001\u0000\u0000\u0000\u00e1\u00e2\u0005"+
		"\u000f\u0000\u0000\u00e2\u00e3\u0005\u0003\u0000\u0000\u00e3\u00e4\u0003"+
		"$\u0012\u0000\u00e4\u00e5\u0005\u0005\u0000\u0000\u00e5\u00e6\u0003\u0002"+
		"\u0001\u0000\u00e6\u0015\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\u0010"+
		"\u0000\u0000\u00e8\u0017\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0011"+
		"\u0000\u0000\u00ea\u0019\u0001\u0000\u0000\u0000\u00eb\u00ef\u0005\u0012"+
		"\u0000\u0000\u00ec\u00ee\u0003\u0002\u0001\u0000\u00ed\u00ec\u0001\u0000"+
		"\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005\u0013"+
		"\u0000\u0000\u00f3\u001b\u0001\u0000\u0000\u0000\u00f4\u00f6\u0005\u0014"+
		"\u0000\u0000\u00f5\u00f7\u0003$\u0012\u0000\u00f6\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u001d\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f9\u0005\u0015\u0000\u0000\u00f9\u00fa\u0005\u0003\u0000"+
		"\u0000\u00fa\u00ff\u0005.\u0000\u0000\u00fb\u00fc\u0005\u0004\u0000\u0000"+
		"\u00fc\u00fe\u0005.\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fe"+
		"\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u00ff"+
		"\u0100\u0001\u0000\u0000\u0000\u0100\u0102\u0001\u0000\u0000\u0000\u0101"+
		"\u00ff\u0001\u0000\u0000\u0000\u0102\u0103\u0005\u0005\u0000\u0000\u0103"+
		"\u0104\u0003\u001a\r\u0000\u0104\u001f\u0001\u0000\u0000\u0000\u0105\u0106"+
		"\u0005\u0016\u0000\u0000\u0106\u0107\u0005\u0003\u0000\u0000\u0107\u010c"+
		"\u0003$\u0012\u0000\u0108\u0109\u0005\u0004\u0000\u0000\u0109\u010b\u0003"+
		"$\u0012\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000"+
		"\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u010f\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0005\u0005\u0000\u0000\u0110!\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0005\u0017\u0000\u0000\u0112\u0113\u0005\u0003\u0000"+
		"\u0000\u0113\u0114\u0005.\u0000\u0000\u0114\u0115\u0005\u0005\u0000\u0000"+
		"\u0115#\u0001\u0000\u0000\u0000\u0116\u0117\u0003&\u0013\u0000\u0117\u0118"+
		"\u0007\u0001\u0000\u0000\u0118\u0119\u0003$\u0012\u0000\u0119\u011c\u0001"+
		"\u0000\u0000\u0000\u011a\u011c\u0003&\u0013\u0000\u011b\u0116\u0001\u0000"+
		"\u0000\u0000\u011b\u011a\u0001\u0000\u0000\u0000\u011c%\u0001\u0000\u0000"+
		"\u0000\u011d\u011e\u0003(\u0014\u0000\u011e\u011f\u0007\u0002\u0000\u0000"+
		"\u011f\u0120\u0003&\u0013\u0000\u0120\u0123\u0001\u0000\u0000\u0000\u0121"+
		"\u0123\u0003(\u0014\u0000\u0122\u011d\u0001\u0000\u0000\u0000\u0122\u0121"+
		"\u0001\u0000\u0000\u0000\u0123\'\u0001\u0000\u0000\u0000\u0124\u0125\u0003"+
		"*\u0015\u0000\u0125\u0126\u0007\u0003\u0000\u0000\u0126\u0127\u0003(\u0014"+
		"\u0000\u0127\u012a\u0001\u0000\u0000\u0000\u0128\u012a\u0003*\u0015\u0000"+
		"\u0129\u0124\u0001\u0000\u0000\u0000\u0129\u0128\u0001\u0000\u0000\u0000"+
		"\u012a)\u0001\u0000\u0000\u0000\u012b\u012c\u0003,\u0016\u0000\u012c\u012d"+
		"\u0007\u0004\u0000\u0000\u012d\u012e\u0003*\u0015\u0000\u012e\u0131\u0001"+
		"\u0000\u0000\u0000\u012f\u0131\u0003,\u0016\u0000\u0130\u012b\u0001\u0000"+
		"\u0000\u0000\u0130\u012f\u0001\u0000\u0000\u0000\u0131+\u0001\u0000\u0000"+
		"\u0000\u0132\u0133\u0003.\u0017\u0000\u0133\u0134\u0007\u0005\u0000\u0000"+
		"\u0134\u0135\u0003,\u0016\u0000\u0135\u0138\u0001\u0000\u0000\u0000\u0136"+
		"\u0138\u0003.\u0017\u0000\u0137\u0132\u0001\u0000\u0000\u0000\u0137\u0136"+
		"\u0001\u0000\u0000\u0000\u0138-\u0001\u0000\u0000\u0000\u0139\u013a\u0003"+
		"4\u001a\u0000\u013a\u013b\u0005%\u0000\u0000\u013b\u013c\u0003.\u0017"+
		"\u0000\u013c\u013f\u0001\u0000\u0000\u0000\u013d\u013f\u00034\u001a\u0000"+
		"\u013e\u0139\u0001\u0000\u0000\u0000\u013e\u013d\u0001\u0000\u0000\u0000"+
		"\u013f/\u0001\u0000\u0000\u0000\u0140\u0141\u0005\t\u0000\u0000\u0141"+
		"\u0146\u0003$\u0012\u0000\u0142\u0143\u0005\u0004\u0000\u0000\u0143\u0145"+
		"\u0003$\u0012\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0145\u0148\u0001"+
		"\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0146\u0147\u0001"+
		"\u0000\u0000\u0000\u0147\u0149\u0001\u0000\u0000\u0000\u0148\u0146\u0001"+
		"\u0000\u0000\u0000\u0149\u014a\u0005\n\u0000\u0000\u014a1\u0001\u0000"+
		"\u0000\u0000\u014b\u014c\u0005.\u0000\u0000\u014c\u014d\u0005\t\u0000"+
		"\u0000\u014d\u014e\u0003$\u0012\u0000\u014e\u014f\u0005\n\u0000\u0000"+
		"\u014f3\u0001\u0000\u0000\u0000\u0150\u0151\u0005!\u0000\u0000\u0151\u0169"+
		"\u00034\u001a\u0000\u0152\u0169\u00036\u001b\u0000\u0153\u015a\u0005."+
		"\u0000\u0000\u0154\u0155\u0005\t\u0000\u0000\u0155\u0156\u0003$\u0012"+
		"\u0000\u0156\u0157\u0005\n\u0000\u0000\u0157\u0159\u0001\u0000\u0000\u0000"+
		"\u0158\u0154\u0001\u0000\u0000\u0000\u0159\u015c\u0001\u0000\u0000\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000"+
		"\u015b\u0169\u0001\u0000\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000"+
		"\u015d\u0169\u00030\u0018\u0000\u015e\u0169\u0005&\u0000\u0000\u015f\u0169"+
		"\u0005\'\u0000\u0000\u0160\u0169\u0005(\u0000\u0000\u0161\u0169\u0005"+
		")\u0000\u0000\u0162\u0169\u0005*\u0000\u0000\u0163\u0169\u0005+\u0000"+
		"\u0000\u0164\u0165\u0005\u0003\u0000\u0000\u0165\u0166\u0003$\u0012\u0000"+
		"\u0166\u0167\u0005\u0005\u0000\u0000\u0167\u0169\u0001\u0000\u0000\u0000"+
		"\u0168\u0150\u0001\u0000\u0000\u0000\u0168\u0152\u0001\u0000\u0000\u0000"+
		"\u0168\u0153\u0001\u0000\u0000\u0000\u0168\u015d\u0001\u0000\u0000\u0000"+
		"\u0168\u015e\u0001\u0000\u0000\u0000\u0168\u015f\u0001\u0000\u0000\u0000"+
		"\u0168\u0160\u0001\u0000\u0000\u0000\u0168\u0161\u0001\u0000\u0000\u0000"+
		"\u0168\u0162\u0001\u0000\u0000\u0000\u0168\u0163\u0001\u0000\u0000\u0000"+
		"\u0168\u0164\u0001\u0000\u0000\u0000\u01695\u0001\u0000\u0000\u0000\u016a"+
		"\u016b\u0005.\u0000\u0000\u016b\u0174\u0005\u0003\u0000\u0000\u016c\u0171"+
		"\u0003$\u0012\u0000\u016d\u016e\u0005\u0004\u0000\u0000\u016e\u0170\u0003"+
		"$\u0012\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u0170\u0173\u0001\u0000"+
		"\u0000\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0171\u0172\u0001\u0000"+
		"\u0000\u0000\u0172\u0175\u0001\u0000\u0000\u0000\u0173\u0171\u0001\u0000"+
		"\u0000\u0000\u0174\u016c\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000"+
		"\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177\u0005\u0005"+
		"\u0000\u0000\u01777\u0001\u0000\u0000\u0000 ;glw}\u0084\u0087\u008b\u0094"+
		"\u00a6\u00b2\u00b7\u00bc\u00c1\u00cd\u00d2\u00d9\u00ef\u00f6\u00ff\u010c"+
		"\u011b\u0122\u0129\u0130\u0137\u013e\u0146\u015a\u0168\u0171\u0174";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}