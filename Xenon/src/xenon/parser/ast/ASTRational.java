/* Generated By:JJTree: Do not edit this line. ASTRational.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTRational extends SimpleNode {
  public ASTRational(int id) {
    super(id);
  }

  public ASTRational(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=9c85cf829d906132c6c0c735f59f17cd (do not edit this line) */
