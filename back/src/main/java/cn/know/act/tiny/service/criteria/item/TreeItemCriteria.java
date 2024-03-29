package cn.know.act.tiny.service.criteria.item;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import cn.know.act.proton.core.service.filter.StringFilter;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import cn.know.act.proton.core.service.filter.LongFilter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * tree测试item
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
public class TreeItemCriteria implements Serializable {

    /**
     * 名称
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter name;

    /**
     * 值
     */
    @Generated(IRW.CODE_GENERATOR)
    private StringFilter value;

    /**
     * test
     */
    @Generated(IRW.CODE_GENERATOR)
    @ToString.Exclude
    private LongFilter tests;

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getName() {
        return name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setName(StringFilter name) {
        this.name = name;
    }

    @Generated(IRW.CODE_GENERATOR)
    public StringFilter getValue() {
        return value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setValue(StringFilter value) {
        this.value = value;
    }

    @Generated(IRW.CODE_GENERATOR)
    public LongFilter getTests() {
        return tests;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTests(LongFilter tests) {
        this.tests = tests;
    }
}
