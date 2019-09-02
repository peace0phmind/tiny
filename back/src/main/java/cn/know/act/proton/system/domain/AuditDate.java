package cn.know.act.proton.system.domain;

import java.time.LocalDateTime;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;

public interface AuditDate {

    @Generated(IRW.CODE_GENERATOR)
    LocalDateTime getCreatedDate();

    @Generated(IRW.CODE_GENERATOR)
    void setCreatedDate(LocalDateTime createdDate);

    @Generated(IRW.CODE_GENERATOR)
    LocalDateTime getModifiedDate();

    @Generated(IRW.CODE_GENERATOR)
    void setModifiedDate(LocalDateTime modifiedDate);
}
