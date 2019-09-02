package cn.know.act.proton.system.domain;

import java.io.Serializable;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import cn.know.act.proton.core.util.IRW;
import javax.annotation.Generated;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import cn.know.act.proton.core.service.filter.Filter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.Objects;

/**
 * 操作权限
 */
@ToString
@NoArgsConstructor
@DynamicUpdate
@Entity
@DiscriminatorValue(value = "OP")
public class OperationPerm extends Permission implements Serializable {

    /**
     * 操作类型
     */
    @Generated(IRW.CODE_GENERATOR)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "op_type", length = 15)
    private OperationPerm.Type type;

    @Generated(IRW.CODE_GENERATOR)
    public boolean isOperationPerm() {
        return true;
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPerm asOperationPerm() {
        return this;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void ifOperationPerm(Consumer<OperationPerm> action) {
        action.accept(this);
    }

    @Generated(IRW.CODE_GENERATOR)
    public Optional<OperationPerm> toOperationPerm() {
        return Optional.of(this);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationPerm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OperationPerm that = (OperationPerm) o;
        return Objects.equals(type, that.type);
    }

    @Override
    @Generated(IRW.CODE_GENERATOR)
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Generated(IRW.CODE_GENERATOR)
    public OperationPerm.Type getType() {
        return type;
    }

    @Generated(IRW.CODE_GENERATOR)
    public void setType(OperationPerm.Type type) {
        this.type = type;
    }

    /**
     * 类型
     */
    public enum Type {

        /**
         * 新增
         */
        CREATE,
        /**
         * 读取
         */
        READ,
        /**
         * 更新
         */
        UPDATE,
        /**
         * 删除
         */
        DELETE,
        /**
         * 移动
         */
        MOVE,
        /**
         * 开始流程
         */
        BPM_START,
        /**
         * 取消流程
         */
        BPM_CANCEL,
        /**
         * 签收任务
         */
        BPM_CLAIM,
        /**
         * 完成任务
         */
        BPM_COMPLETE
    }

    public static class TypeFilter extends Filter<Type> {
    }
}
