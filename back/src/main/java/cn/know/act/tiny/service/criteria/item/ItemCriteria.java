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
import javax.persistence.Transient;

/**
 * 测试item
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "_type")
public abstract class ItemCriteria implements Serializable {

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
    private LongFilter test;

    @Generated(IRW.CODE_GENERATOR)
    @Transient
    private _Type _type = _Type.valueOf(this.getClass());

    @Generated(IRW.CODE_GENERATOR)
    public _Type get_type() {
        return this._type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void set_type(_Type _type) {
        this._type = _type;
    }

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
    public LongFilter getTest() {
        return test;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setTest(LongFilter test) {
        this.test = test;
    }

    public enum _Type {
        ;

        private Class field;

        @Generated(IRW.CODE_GENERATOR)
        _Type(Class field) {
            this.field = field;
        }

        @Generated(IRW.CODE_GENERATOR)
        public static _Type valueOf(Class field) {
            for (_Type type : values()) {
                if (type.field.equals(field)) {
                    return type;
                }
            }
            return null;
        }

        @Generated(IRW.CODE_GENERATOR)
        public Class getField() {
            return field;
        }
    }
}
