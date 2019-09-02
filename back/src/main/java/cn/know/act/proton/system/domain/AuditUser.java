package cn.know.act.proton.system.domain;

import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;

public interface AuditUser {

    @Generated(IRW.CODE_GENERATOR)
    User getCreator();

    @Generated(IRW.CODE_GENERATOR)
    void setCreator(User creator);

    @Generated(IRW.CODE_GENERATOR)
    User getModifier();

    @Generated(IRW.CODE_GENERATOR)
    void setModifier(User modifier);
}
