/* Generated By:JJTree: Do not edit this line. ASTClassDef.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=xenon.interpreter.BaseASTNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package xenon.parser.ast;

public
class ASTClassDef extends SimpleNode {
  public ASTClassDef(int id) {
    super(id);
  }

  public ASTClassDef(Xenon p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(XenonVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1f3e191a6f6304ad4110232b7bc5fcb7 (do not edit this line) */