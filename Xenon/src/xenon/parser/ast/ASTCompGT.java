/* Generated By:JJTree: Do not edit this line. ASTCompGT.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTCompGT extends SimpleNode {
  public ASTCompGT(int id) {
    super(id);
  }

  public ASTCompGT(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c822d855a10cc78dfaab6125ca05a0c1 (do not edit this line) */
