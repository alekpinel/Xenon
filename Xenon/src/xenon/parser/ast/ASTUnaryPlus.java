/* Generated By:JJTree: Do not edit this line. ASTUnaryPlus.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTUnaryPlus extends SimpleNode {
  public ASTUnaryPlus(int id) {
    super(id);
  }

  public ASTUnaryPlus(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1adc8a4ff2afd75ece4f4fdb8b2db563 (do not edit this line) */