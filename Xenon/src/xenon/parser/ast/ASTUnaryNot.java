/* Generated By:JJTree: Do not edit this line. ASTUnaryNot.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTUnaryNot extends SimpleNode {
  public ASTUnaryNot(int id) {
    super(id);
  }

  public ASTUnaryNot(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=15ede3429664b8ab5f4e4c93747ffd53 (do not edit this line) */
