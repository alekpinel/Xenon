/* Generated By:JJTree: Do not edit this line. ASTThis.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTThis extends SimpleNode {
  public ASTThis(int id) {
    super(id);
  }

  public ASTThis(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b11dd1c3d6e33c91ebe9e5a487888ba4 (do not edit this line) */
