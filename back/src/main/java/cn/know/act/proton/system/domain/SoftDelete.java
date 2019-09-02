package cn.know.act.proton.system.domain;

import java.time.LocalDateTime;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;

public interface SoftDelete {

    @Generated(IRW.CODE_GENERATOR)
    LocalDateTime getDeletedDate();

    @Generated(IRW.CODE_GENERATOR)
    void setDeletedDate(LocalDateTime deletedDate);
}
