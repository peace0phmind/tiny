package cn.know.act.proton.system.domain;

import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;

public interface VersionLock {

    @Generated(IRW.CODE_GENERATOR)
    Integer get_version();

    @Generated(IRW.CODE_GENERATOR)
    void set_version(Integer _version);
}
