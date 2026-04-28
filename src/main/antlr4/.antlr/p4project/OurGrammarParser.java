// Generated from c:/Users/Mikke/Documents/GitHub/P4_Project/src/main/antlr4/OurGrammar.g4 by ANTLR 4.13.1

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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		INT=39, FLOAT=40, BOOL=41, CHAR=42, STRING=43, THREAD=44, PREFIX=45, TYPE=46, 
		ID=47, LINE_COMMENT=48, BLOCK_COMMENT=49, WS=50;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_assignment = 2, RULE_assVar = 3, 
		RULE_assFunc = 4, RULE_reassignment = 5, RULE_declaration = 6, RULE_threadAssignment = 7, 
		RULE_awaitStatement = 8, RULE_castExpression = 9, RULE_typeRef = 10, RULE_ifStatement = 11, 
		RULE_forStatement = 12, RULE_forVar = 13, RULE_whileStatement = 14, RULE_continueStatement = 15, 
		RULE_breakStatement = 16, RULE_block = 17, RULE_returnStatement = 18, 
		RULE_criticalSection = 19, RULE_printStatement = 20, RULE_readStatement = 21, 
		RULE_expr = 22, RULE_equal = 23, RULE_comp = 24, RULE_additive = 25, RULE_mult = 26, 
		RULE_power = 27, RULE_arrayLiteral = 28, RULE_arrayIndex = 29, RULE_factor = 30, 
		RULE_functionCall = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "assignment", "assVar", "assFunc", "reassignment", 
			"declaration", "threadAssignment", "awaitStatement", "castExpression", 
			"typeRef", "ifStatement", "forStatement", "forVar", "whileStatement", 
			"continueStatement", "breakStatement", "block", "returnStatement", "criticalSection", 
			"printStatement", "readStatement", "expr", "equal", "comp", "additive", 
			"mult", "power", "arrayLiteral", "arrayIndex", "factor", "functionCall"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "','", "')'", "'=>'", "'await'", "'awaitAny'", 
			"'cast'", "'['", "']'", "'if'", "'else if'", "'else'", "'for'", "'while'", 
			"'continue'", "'break'", "'{'", "'}'", "'return'", "'critical'", "'print'", 
			"'read'", "'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'^'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "INT", "FLOAT", "BOOL", "CHAR", "STRING", "THREAD", 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 280942433245064L) != 0)) {
				{
				{
				setState(64);
				statement();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				expr();
				setState(73);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				arrayIndex();
				setState(76);
				match(T__1);
				setState(77);
				expr();
				setState(78);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				assignment();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				reassignment();
				setState(82);
				match(T__0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(84);
				declaration();
				setState(85);
				match(T__0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(87);
				threadAssignment();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(88);
				awaitStatement();
				setState(89);
				match(T__0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(91);
				ifStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(92);
				forStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(93);
				whileStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(94);
				continueStatement();
				setState(95);
				match(T__0);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(97);
				breakStatement();
				setState(98);
				match(T__0);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(100);
				block();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(101);
				returnStatement();
				setState(102);
				match(T__0);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(104);
				criticalSection();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(105);
				printStatement();
				setState(106);
				match(T__0);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(108);
				readStatement();
				setState(109);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREFIX) {
				{
				setState(113);
				match(PREFIX);
				}
			}

			setState(116);
			typeRef();
			setState(117);
			match(ID);
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(118);
				assVar();
				setState(119);
				match(T__0);
				}
				break;
			case T__2:
				{
				setState(121);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitAssVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssVarContext assVar() throws RecognitionException {
		AssVarContext _localctx = new AssVarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__1);
			setState(125);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitAssFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssFuncContext assFunc() throws RecognitionException {
		AssFuncContext _localctx = new AssFuncContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__2);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREFIX || _la==TYPE) {
				{
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PREFIX) {
					{
					setState(128);
					match(PREFIX);
					}
				}

				setState(131);
				typeRef();
				setState(132);
				match(ID);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(133);
					match(T__3);
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==PREFIX) {
						{
						setState(134);
						match(PREFIX);
						}
					}

					setState(137);
					typeRef();
					setState(138);
					match(ID);
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(147);
			match(T__4);
			setState(148);
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
		public ReassignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reassignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitReassignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReassignmentContext reassignment() throws RecognitionException {
		ReassignmentContext _localctx = new ReassignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reassignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(ID);
			setState(151);
			match(T__1);
			setState(152);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PREFIX) {
				{
				{
				setState(154);
				match(PREFIX);
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(160);
			typeRef();
			setState(161);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitThreadAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThreadAssignmentContext threadAssignment() throws RecognitionException {
		ThreadAssignmentContext _localctx = new ThreadAssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_threadAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			typeRef();
			setState(164);
			match(ID);
			setState(165);
			match(T__5);
			setState(166);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitAwaitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AwaitStatementContext awaitStatement() throws RecognitionException {
		AwaitStatementContext _localctx = new AwaitStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_awaitStatement);
		int _la;
		try {
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(T__6);
				setState(169);
				match(T__2);
				setState(170);
				expr();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(171);
					match(T__3);
					setState(172);
					expr();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(178);
				match(T__4);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(T__7);
				setState(181);
				match(T__2);
				setState(182);
				expr();
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(183);
					match(T__3);
					setState(184);
					expr();
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(190);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_castExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__8);
			setState(195);
			match(T__2);
			setState(196);
			match(TYPE);
			setState(197);
			match(T__4);
			setState(198);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(TYPE);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(201);
				match(T__9);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT || _la==ID) {
					{
					setState(202);
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

				setState(205);
				match(T__10);
				}
				}
				setState(210);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(T__11);
			setState(212);
			match(T__2);
			setState(213);
			expr();
			setState(214);
			match(T__4);
			setState(215);
			statement();
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(216);
					match(T__12);
					setState(217);
					statement();
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(223);
				match(T__13);
				setState(224);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(T__14);
			setState(228);
			match(T__2);
			setState(229);
			forVar();
			setState(230);
			match(T__0);
			setState(231);
			expr();
			setState(232);
			match(T__0);
			setState(233);
			reassignment();
			setState(234);
			match(T__4);
			setState(235);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitForVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForVarContext forVar() throws RecognitionException {
		ForVarContext _localctx = new ForVarContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forVar);
		int _la;
		try {
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PREFIX) {
					{
					setState(237);
					match(PREFIX);
					}
				}

				setState(240);
				typeRef();
				setState(241);
				match(ID);
				setState(244);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(242);
					assVar();
					}
					break;
				case T__2:
					{
					setState(243);
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
				setState(246);
				reassignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__15);
			setState(251);
			match(T__2);
			setState(252);
			expr();
			setState(253);
			match(T__4);
			setState(254);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__18);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 280942433245064L) != 0)) {
				{
				{
				setState(261);
				statement();
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(267);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(T__20);
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 175389284501000L) != 0)) {
				{
				setState(270);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitCriticalSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriticalSectionContext criticalSection() throws RecognitionException {
		CriticalSectionContext _localctx = new CriticalSectionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_criticalSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__21);
			setState(274);
			match(T__2);
			setState(275);
			match(ID);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(276);
				match(T__3);
				setState(277);
				match(ID);
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(283);
			match(T__4);
			setState(284);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__22);
			setState(287);
			match(T__2);
			setState(288);
			expr();
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(289);
				match(T__3);
				setState(290);
				expr();
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(296);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitReadStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_readStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(T__23);
			setState(299);
			match(T__2);
			setState(300);
			match(ID);
			setState(301);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expr);
		int _la;
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				equal();
				setState(304);
				_la = _input.LA(1);
				if ( !(_la==T__24 || _la==T__25) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(305);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualContext equal() throws RecognitionException {
		EqualContext _localctx = new EqualContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_equal);
		int _la;
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				comp();
				setState(311);
				_la = _input.LA(1);
				if ( !(_la==T__26 || _la==T__27) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(312);
				equal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitComp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_comp);
		int _la;
		try {
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				additive();
				setState(318);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(319);
				comp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitAdditive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveContext additive() throws RecognitionException {
		AdditiveContext _localctx = new AdditiveContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_additive);
		int _la;
		try {
			setState(329);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				mult();
				setState(325);
				_la = _input.LA(1);
				if ( !(_la==T__32 || _la==T__33) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(326);
				additive();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultContext mult() throws RecognitionException {
		MultContext _localctx = new MultContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_mult);
		int _la;
		try {
			setState(336);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				power();
				setState(332);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(333);
				mult();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_power);
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				factor();
				setState(339);
				match(T__37);
				setState(340);
				power();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			match(T__9);
			setState(346);
			expr();
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(347);
				match(T__3);
				setState(348);
				expr();
				}
				}
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(354);
			match(T__10);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitArrayIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_arrayIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(ID);
			setState(357);
			match(T__9);
			setState(358);
			expr();
			setState(359);
			match(T__10);
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(360);
				match(T__9);
				setState(361);
				expr();
				setState(362);
				match(T__10);
				}
				}
				setState(368);
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
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_factor);
		int _la;
		try {
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				match(T__33);
				setState(370);
				factor();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				functionCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				match(ID);
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(373);
					match(T__9);
					setState(374);
					expr();
					setState(375);
					match(T__10);
					}
					}
					setState(381);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(382);
				arrayLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(383);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(384);
				match(FLOAT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(385);
				match(BOOL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(386);
				match(CHAR);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(387);
				match(STRING);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(388);
				match(THREAD);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(389);
				match(T__2);
				setState(390);
				expr();
				setState(391);
				match(T__4);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(393);
				castExpression();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OurGrammarVisitor ) return ((OurGrammarVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(ID);
			setState(397);
			match(T__2);
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 175389284501000L) != 0)) {
				{
				setState(398);
				expr();
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(399);
					match(T__3);
					setState(400);
					expr();
					}
					}
					setState(405);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(408);
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
		"\u0004\u00012\u019b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0005\u0000B\b\u0000\n\u0000\f\u0000"+
		"E\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001p\b\u0001\u0001\u0002\u0003\u0002s\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002{\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0003"+
		"\u0004\u0082\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004\u0088\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u008d"+
		"\b\u0004\n\u0004\f\u0004\u0090\t\u0004\u0003\u0004\u0092\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0005\u0006\u009c\b\u0006\n\u0006\f\u0006\u009f\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u00ae\b\b\n\b\f\b\u00b1\t\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0005\b\u00ba\b\b\n\b\f\b\u00bd\t\b\u0001\b\u0001\b\u0003"+
		"\b\u00c1\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u00cc\b\n\u0001\n\u0005\n\u00cf\b\n\n\n\f\n\u00d2\t"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00db\b\u000b\n\u000b\f\u000b\u00de\t\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00e2\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0003\r\u00ef"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00f5\b\r\u0001\r\u0001\r"+
		"\u0003\r\u00f9\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u0107\b\u0011\n\u0011\f\u0011\u010a\t\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u0110\b\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u0117\b\u0013\n\u0013\f\u0013\u011a\t\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u0124\b\u0014\n\u0014\f\u0014\u0127\t\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0135\b\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u013c\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0143\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u014a\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u0151\b\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0158\b\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u015e\b\u001c\n\u001c"+
		"\f\u001c\u0161\t\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0005\u001d\u016d\b\u001d\n\u001d\f\u001d\u0170\t\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0005\u001e\u017a\b\u001e\n\u001e\f\u001e\u017d\t\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u018b\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0005\u001f\u0192\b\u001f\n\u001f\f\u001f\u0195\t\u001f\u0003\u001f\u0197"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0000\u0000 \u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>\u0000\u0006\u0002\u0000\'\'//\u0001\u0000\u0019\u001a"+
		"\u0001\u0000\u001b\u001c\u0001\u0000\u001d \u0001\u0000!\"\u0001\u0000"+
		"#%\u01b7\u0000C\u0001\u0000\u0000\u0000\u0002o\u0001\u0000\u0000\u0000"+
		"\u0004r\u0001\u0000\u0000\u0000\u0006|\u0001\u0000\u0000\u0000\b\u007f"+
		"\u0001\u0000\u0000\u0000\n\u0096\u0001\u0000\u0000\u0000\f\u009d\u0001"+
		"\u0000\u0000\u0000\u000e\u00a3\u0001\u0000\u0000\u0000\u0010\u00c0\u0001"+
		"\u0000\u0000\u0000\u0012\u00c2\u0001\u0000\u0000\u0000\u0014\u00c8\u0001"+
		"\u0000\u0000\u0000\u0016\u00d3\u0001\u0000\u0000\u0000\u0018\u00e3\u0001"+
		"\u0000\u0000\u0000\u001a\u00f8\u0001\u0000\u0000\u0000\u001c\u00fa\u0001"+
		"\u0000\u0000\u0000\u001e\u0100\u0001\u0000\u0000\u0000 \u0102\u0001\u0000"+
		"\u0000\u0000\"\u0104\u0001\u0000\u0000\u0000$\u010d\u0001\u0000\u0000"+
		"\u0000&\u0111\u0001\u0000\u0000\u0000(\u011e\u0001\u0000\u0000\u0000*"+
		"\u012a\u0001\u0000\u0000\u0000,\u0134\u0001\u0000\u0000\u0000.\u013b\u0001"+
		"\u0000\u0000\u00000\u0142\u0001\u0000\u0000\u00002\u0149\u0001\u0000\u0000"+
		"\u00004\u0150\u0001\u0000\u0000\u00006\u0157\u0001\u0000\u0000\u00008"+
		"\u0159\u0001\u0000\u0000\u0000:\u0164\u0001\u0000\u0000\u0000<\u018a\u0001"+
		"\u0000\u0000\u0000>\u018c\u0001\u0000\u0000\u0000@B\u0003\u0002\u0001"+
		"\u0000A@\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001\u0000"+
		"\u0000\u0000CD\u0001\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000EC\u0001"+
		"\u0000\u0000\u0000FG\u0005\u0000\u0000\u0001G\u0001\u0001\u0000\u0000"+
		"\u0000HI\u0003,\u0016\u0000IJ\u0005\u0001\u0000\u0000Jp\u0001\u0000\u0000"+
		"\u0000KL\u0003:\u001d\u0000LM\u0005\u0002\u0000\u0000MN\u0003,\u0016\u0000"+
		"NO\u0005\u0001\u0000\u0000Op\u0001\u0000\u0000\u0000Pp\u0003\u0004\u0002"+
		"\u0000QR\u0003\n\u0005\u0000RS\u0005\u0001\u0000\u0000Sp\u0001\u0000\u0000"+
		"\u0000TU\u0003\f\u0006\u0000UV\u0005\u0001\u0000\u0000Vp\u0001\u0000\u0000"+
		"\u0000Wp\u0003\u000e\u0007\u0000XY\u0003\u0010\b\u0000YZ\u0005\u0001\u0000"+
		"\u0000Zp\u0001\u0000\u0000\u0000[p\u0003\u0016\u000b\u0000\\p\u0003\u0018"+
		"\f\u0000]p\u0003\u001c\u000e\u0000^_\u0003\u001e\u000f\u0000_`\u0005\u0001"+
		"\u0000\u0000`p\u0001\u0000\u0000\u0000ab\u0003 \u0010\u0000bc\u0005\u0001"+
		"\u0000\u0000cp\u0001\u0000\u0000\u0000dp\u0003\"\u0011\u0000ef\u0003$"+
		"\u0012\u0000fg\u0005\u0001\u0000\u0000gp\u0001\u0000\u0000\u0000hp\u0003"+
		"&\u0013\u0000ij\u0003(\u0014\u0000jk\u0005\u0001\u0000\u0000kp\u0001\u0000"+
		"\u0000\u0000lm\u0003*\u0015\u0000mn\u0005\u0001\u0000\u0000np\u0001\u0000"+
		"\u0000\u0000oH\u0001\u0000\u0000\u0000oK\u0001\u0000\u0000\u0000oP\u0001"+
		"\u0000\u0000\u0000oQ\u0001\u0000\u0000\u0000oT\u0001\u0000\u0000\u0000"+
		"oW\u0001\u0000\u0000\u0000oX\u0001\u0000\u0000\u0000o[\u0001\u0000\u0000"+
		"\u0000o\\\u0001\u0000\u0000\u0000o]\u0001\u0000\u0000\u0000o^\u0001\u0000"+
		"\u0000\u0000oa\u0001\u0000\u0000\u0000od\u0001\u0000\u0000\u0000oe\u0001"+
		"\u0000\u0000\u0000oh\u0001\u0000\u0000\u0000oi\u0001\u0000\u0000\u0000"+
		"ol\u0001\u0000\u0000\u0000p\u0003\u0001\u0000\u0000\u0000qs\u0005-\u0000"+
		"\u0000rq\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0001\u0000"+
		"\u0000\u0000tu\u0003\u0014\n\u0000uz\u0005/\u0000\u0000vw\u0003\u0006"+
		"\u0003\u0000wx\u0005\u0001\u0000\u0000x{\u0001\u0000\u0000\u0000y{\u0003"+
		"\b\u0004\u0000zv\u0001\u0000\u0000\u0000zy\u0001\u0000\u0000\u0000{\u0005"+
		"\u0001\u0000\u0000\u0000|}\u0005\u0002\u0000\u0000}~\u0003,\u0016\u0000"+
		"~\u0007\u0001\u0000\u0000\u0000\u007f\u0091\u0005\u0003\u0000\u0000\u0080"+
		"\u0082\u0005-\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0003\u0014\n\u0000\u0084\u008e\u0005/\u0000\u0000\u0085\u0087\u0005"+
		"\u0004\u0000\u0000\u0086\u0088\u0005-\u0000\u0000\u0087\u0086\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0003\u0014\n\u0000\u008a\u008b\u0005/\u0000"+
		"\u0000\u008b\u008d\u0001\u0000\u0000\u0000\u008c\u0085\u0001\u0000\u0000"+
		"\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000"+
		"\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0081\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0005\u0005\u0000\u0000\u0094\u0095\u0003\"\u0011\u0000"+
		"\u0095\t\u0001\u0000\u0000\u0000\u0096\u0097\u0005/\u0000\u0000\u0097"+
		"\u0098\u0005\u0002\u0000\u0000\u0098\u0099\u0003,\u0016\u0000\u0099\u000b"+
		"\u0001\u0000\u0000\u0000\u009a\u009c\u0005-\u0000\u0000\u009b\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u00a0\u0001"+
		"\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0003"+
		"\u0014\n\u0000\u00a1\u00a2\u0005/\u0000\u0000\u00a2\r\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0003\u0014\n\u0000\u00a4\u00a5\u0005/\u0000\u0000"+
		"\u00a5\u00a6\u0005\u0006\u0000\u0000\u00a6\u00a7\u0003\"\u0011\u0000\u00a7"+
		"\u000f\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u0007\u0000\u0000\u00a9"+
		"\u00aa\u0005\u0003\u0000\u0000\u00aa\u00af\u0003,\u0016\u0000\u00ab\u00ac"+
		"\u0005\u0004\u0000\u0000\u00ac\u00ae\u0003,\u0016\u0000\u00ad\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001"+
		"\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005"+
		"\u0005\u0000\u0000\u00b3\u00c1\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005"+
		"\b\u0000\u0000\u00b5\u00b6\u0005\u0003\u0000\u0000\u00b6\u00bb\u0003,"+
		"\u0016\u0000\u00b7\u00b8\u0005\u0004\u0000\u0000\u00b8\u00ba\u0003,\u0016"+
		"\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\u00be\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0005\u0005\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c0\u00a8\u0001\u0000\u0000\u0000\u00c0\u00b4\u0001\u0000\u0000"+
		"\u0000\u00c1\u0011\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\t\u0000\u0000"+
		"\u00c3\u00c4\u0005\u0003\u0000\u0000\u00c4\u00c5\u0005.\u0000\u0000\u00c5"+
		"\u00c6\u0005\u0005\u0000\u0000\u00c6\u00c7\u0003,\u0016\u0000\u00c7\u0013"+
		"\u0001\u0000\u0000\u0000\u00c8\u00d0\u0005.\u0000\u0000\u00c9\u00cb\u0005"+
		"\n\u0000\u0000\u00ca\u00cc\u0007\u0000\u0000\u0000\u00cb\u00ca\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0005\u000b\u0000\u0000\u00ce\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u0015\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\f\u0000"+
		"\u0000\u00d4\u00d5\u0005\u0003\u0000\u0000\u00d5\u00d6\u0003,\u0016\u0000"+
		"\u00d6\u00d7\u0005\u0005\u0000\u0000\u00d7\u00dc\u0003\u0002\u0001\u0000"+
		"\u00d8\u00d9\u0005\r\u0000\u0000\u00d9\u00db\u0003\u0002\u0001\u0000\u00da"+
		"\u00d8\u0001\u0000\u0000\u0000\u00db\u00de\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd"+
		"\u00e1\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00df"+
		"\u00e0\u0005\u000e\u0000\u0000\u00e0\u00e2\u0003\u0002\u0001\u0000\u00e1"+
		"\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2"+
		"\u0017\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005\u000f\u0000\u0000\u00e4"+
		"\u00e5\u0005\u0003\u0000\u0000\u00e5\u00e6\u0003\u001a\r\u0000\u00e6\u00e7"+
		"\u0005\u0001\u0000\u0000\u00e7\u00e8\u0003,\u0016\u0000\u00e8\u00e9\u0005"+
		"\u0001\u0000\u0000\u00e9\u00ea\u0003\n\u0005\u0000\u00ea\u00eb\u0005\u0005"+
		"\u0000\u0000\u00eb\u00ec\u0003\u0002\u0001\u0000\u00ec\u0019\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ef\u0005-\u0000\u0000\u00ee\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f0\u00f1\u0003\u0014\n\u0000\u00f1\u00f4\u0005/\u0000\u0000"+
		"\u00f2\u00f5\u0003\u0006\u0003\u0000\u00f3\u00f5\u0003\b\u0004\u0000\u00f4"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f9\u0001\u0000\u0000\u0000\u00f6\u00f9\u0003\n\u0005\u0000\u00f7\u00f9"+
		"\u0005/\u0000\u0000\u00f8\u00ee\u0001\u0000\u0000\u0000\u00f8\u00f6\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f9\u001b\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fb\u0005\u0010\u0000\u0000\u00fb\u00fc\u0005"+
		"\u0003\u0000\u0000\u00fc\u00fd\u0003,\u0016\u0000\u00fd\u00fe\u0005\u0005"+
		"\u0000\u0000\u00fe\u00ff\u0003\u0002\u0001\u0000\u00ff\u001d\u0001\u0000"+
		"\u0000\u0000\u0100\u0101\u0005\u0011\u0000\u0000\u0101\u001f\u0001\u0000"+
		"\u0000\u0000\u0102\u0103\u0005\u0012\u0000\u0000\u0103!\u0001\u0000\u0000"+
		"\u0000\u0104\u0108\u0005\u0013\u0000\u0000\u0105\u0107\u0003\u0002\u0001"+
		"\u0000\u0106\u0105\u0001\u0000\u0000\u0000\u0107\u010a\u0001\u0000\u0000"+
		"\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000"+
		"\u0000\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000"+
		"\u0000\u010b\u010c\u0005\u0014\u0000\u0000\u010c#\u0001\u0000\u0000\u0000"+
		"\u010d\u010f\u0005\u0015\u0000\u0000\u010e\u0110\u0003,\u0016\u0000\u010f"+
		"\u010e\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110"+
		"%\u0001\u0000\u0000\u0000\u0111\u0112\u0005\u0016\u0000\u0000\u0112\u0113"+
		"\u0005\u0003\u0000\u0000\u0113\u0118\u0005/\u0000\u0000\u0114\u0115\u0005"+
		"\u0004\u0000\u0000\u0115\u0117\u0005/\u0000\u0000\u0116\u0114\u0001\u0000"+
		"\u0000\u0000\u0117\u011a\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000"+
		"\u0000\u0000\u0118\u0119\u0001\u0000\u0000\u0000\u0119\u011b\u0001\u0000"+
		"\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011b\u011c\u0005\u0005"+
		"\u0000\u0000\u011c\u011d\u0003\"\u0011\u0000\u011d\'\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0005\u0017\u0000\u0000\u011f\u0120\u0005\u0003\u0000"+
		"\u0000\u0120\u0125\u0003,\u0016\u0000\u0121\u0122\u0005\u0004\u0000\u0000"+
		"\u0122\u0124\u0003,\u0016\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0124"+
		"\u0127\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0125"+
		"\u0126\u0001\u0000\u0000\u0000\u0126\u0128\u0001\u0000\u0000\u0000\u0127"+
		"\u0125\u0001\u0000\u0000\u0000\u0128\u0129\u0005\u0005\u0000\u0000\u0129"+
		")\u0001\u0000\u0000\u0000\u012a\u012b\u0005\u0018\u0000\u0000\u012b\u012c"+
		"\u0005\u0003\u0000\u0000\u012c\u012d\u0005/\u0000\u0000\u012d\u012e\u0005"+
		"\u0005\u0000\u0000\u012e+\u0001\u0000\u0000\u0000\u012f\u0130\u0003.\u0017"+
		"\u0000\u0130\u0131\u0007\u0001\u0000\u0000\u0131\u0132\u0003,\u0016\u0000"+
		"\u0132\u0135\u0001\u0000\u0000\u0000\u0133\u0135\u0003.\u0017\u0000\u0134"+
		"\u012f\u0001\u0000\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0135"+
		"-\u0001\u0000\u0000\u0000\u0136\u0137\u00030\u0018\u0000\u0137\u0138\u0007"+
		"\u0002\u0000\u0000\u0138\u0139\u0003.\u0017\u0000\u0139\u013c\u0001\u0000"+
		"\u0000\u0000\u013a\u013c\u00030\u0018\u0000\u013b\u0136\u0001\u0000\u0000"+
		"\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013c/\u0001\u0000\u0000\u0000"+
		"\u013d\u013e\u00032\u0019\u0000\u013e\u013f\u0007\u0003\u0000\u0000\u013f"+
		"\u0140\u00030\u0018\u0000\u0140\u0143\u0001\u0000\u0000\u0000\u0141\u0143"+
		"\u00032\u0019\u0000\u0142\u013d\u0001\u0000\u0000\u0000\u0142\u0141\u0001"+
		"\u0000\u0000\u0000\u01431\u0001\u0000\u0000\u0000\u0144\u0145\u00034\u001a"+
		"\u0000\u0145\u0146\u0007\u0004\u0000\u0000\u0146\u0147\u00032\u0019\u0000"+
		"\u0147\u014a\u0001\u0000\u0000\u0000\u0148\u014a\u00034\u001a\u0000\u0149"+
		"\u0144\u0001\u0000\u0000\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u014a"+
		"3\u0001\u0000\u0000\u0000\u014b\u014c\u00036\u001b\u0000\u014c\u014d\u0007"+
		"\u0005\u0000\u0000\u014d\u014e\u00034\u001a\u0000\u014e\u0151\u0001\u0000"+
		"\u0000\u0000\u014f\u0151\u00036\u001b\u0000\u0150\u014b\u0001\u0000\u0000"+
		"\u0000\u0150\u014f\u0001\u0000\u0000\u0000\u01515\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0003<\u001e\u0000\u0153\u0154\u0005&\u0000\u0000\u0154\u0155"+
		"\u00036\u001b\u0000\u0155\u0158\u0001\u0000\u0000\u0000\u0156\u0158\u0003"+
		"<\u001e\u0000\u0157\u0152\u0001\u0000\u0000\u0000\u0157\u0156\u0001\u0000"+
		"\u0000\u0000\u01587\u0001\u0000\u0000\u0000\u0159\u015a\u0005\n\u0000"+
		"\u0000\u015a\u015f\u0003,\u0016\u0000\u015b\u015c\u0005\u0004\u0000\u0000"+
		"\u015c\u015e\u0003,\u0016\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015e"+
		"\u0161\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f"+
		"\u0160\u0001\u0000\u0000\u0000\u0160\u0162\u0001\u0000\u0000\u0000\u0161"+
		"\u015f\u0001\u0000\u0000\u0000\u0162\u0163\u0005\u000b\u0000\u0000\u0163"+
		"9\u0001\u0000\u0000\u0000\u0164\u0165\u0005/\u0000\u0000\u0165\u0166\u0005"+
		"\n\u0000\u0000\u0166\u0167\u0003,\u0016\u0000\u0167\u016e\u0005\u000b"+
		"\u0000\u0000\u0168\u0169\u0005\n\u0000\u0000\u0169\u016a\u0003,\u0016"+
		"\u0000\u016a\u016b\u0005\u000b\u0000\u0000\u016b\u016d\u0001\u0000\u0000"+
		"\u0000\u016c\u0168\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000"+
		"\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000"+
		"\u0000\u016f;\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0005\"\u0000\u0000\u0172\u018b\u0003<\u001e\u0000\u0173"+
		"\u018b\u0003>\u001f\u0000\u0174\u017b\u0005/\u0000\u0000\u0175\u0176\u0005"+
		"\n\u0000\u0000\u0176\u0177\u0003,\u0016\u0000\u0177\u0178\u0005\u000b"+
		"\u0000\u0000\u0178\u017a\u0001\u0000\u0000\u0000\u0179\u0175\u0001\u0000"+
		"\u0000\u0000\u017a\u017d\u0001\u0000\u0000\u0000\u017b\u0179\u0001\u0000"+
		"\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c\u018b\u0001\u0000"+
		"\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017e\u018b\u00038\u001c"+
		"\u0000\u017f\u018b\u0005\'\u0000\u0000\u0180\u018b\u0005(\u0000\u0000"+
		"\u0181\u018b\u0005)\u0000\u0000\u0182\u018b\u0005*\u0000\u0000\u0183\u018b"+
		"\u0005+\u0000\u0000\u0184\u018b\u0005,\u0000\u0000\u0185\u0186\u0005\u0003"+
		"\u0000\u0000\u0186\u0187\u0003,\u0016\u0000\u0187\u0188\u0005\u0005\u0000"+
		"\u0000\u0188\u018b\u0001\u0000\u0000\u0000\u0189\u018b\u0003\u0012\t\u0000"+
		"\u018a\u0171\u0001\u0000\u0000\u0000\u018a\u0173\u0001\u0000\u0000\u0000"+
		"\u018a\u0174\u0001\u0000\u0000\u0000\u018a\u017e\u0001\u0000\u0000\u0000"+
		"\u018a\u017f\u0001\u0000\u0000\u0000\u018a\u0180\u0001\u0000\u0000\u0000"+
		"\u018a\u0181\u0001\u0000\u0000\u0000\u018a\u0182\u0001\u0000\u0000\u0000"+
		"\u018a\u0183\u0001\u0000\u0000\u0000\u018a\u0184\u0001\u0000\u0000\u0000"+
		"\u018a\u0185\u0001\u0000\u0000\u0000\u018a\u0189\u0001\u0000\u0000\u0000"+
		"\u018b=\u0001\u0000\u0000\u0000\u018c\u018d\u0005/\u0000\u0000\u018d\u0196"+
		"\u0005\u0003\u0000\u0000\u018e\u0193\u0003,\u0016\u0000\u018f\u0190\u0005"+
		"\u0004\u0000\u0000\u0190\u0192\u0003,\u0016\u0000\u0191\u018f\u0001\u0000"+
		"\u0000\u0000\u0192\u0195\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000"+
		"\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000"+
		"\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0196\u018e\u0001\u0000"+
		"\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u0198\u0001\u0000"+
		"\u0000\u0000\u0198\u0199\u0005\u0005\u0000\u0000\u0199?\u0001\u0000\u0000"+
		"\u0000#Corz\u0081\u0087\u008e\u0091\u009d\u00af\u00bb\u00c0\u00cb\u00d0"+
		"\u00dc\u00e1\u00ee\u00f4\u00f8\u0108\u010f\u0118\u0125\u0134\u013b\u0142"+
		"\u0149\u0150\u0157\u015f\u016e\u017b\u018a\u0193\u0196";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}