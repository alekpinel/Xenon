/* Generated By:JJTree: Do not edit this line. ASTNullLiteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTNullLiteral extends SimpleNode {
  public ASTNullLiteral(int id) {
    super(id);
  }

  public ASTNullLiteral(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dca3eeb626e40c6db6406f7eba85a361 (do not edit this line) */
