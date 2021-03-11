package com.tianqi.common.result;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.validate.ValidateEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一响应结果
 *
 * @author yuantianqi
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestResult<T> {
    private StatusEnum status;
    private List<ValidateEntity> validates;
    private BaseException error;
    private ResultEntity<T> data;

    public static <D> RestResultBuilder<D> builder() {
        return new RestResultBuilder<D>();
    }

    public static <D> PageResultBuilder<D> builderPage() {
        return new PageResultBuilder<D>();
    }

    public static class ResultEntity<T> {
    }

    @Data
    @AllArgsConstructor
    public static class RestResultEntity<T> extends ResultEntity<T> {
        private boolean isOk;
        private T data;
    }

    @Data
    @AllArgsConstructor
    public static class PageResultEntity<T> extends ResultEntity<T> {
        private long total;
        private List<T> rows;
    }

    public static class ResultBuilder<W> {
        protected final List<ValidateEntity> validates = new ArrayList<>();
        protected StatusEnum status;
        protected BaseException error;


        protected void setStatus(StatusEnum status) {
            this.status = status;
        }

        protected void setValidate(ValidateEntity validate) {
            this.validates.add(validate);
        }

        protected void setValidates(List<ValidateEntity> validates) {
            for (ValidateEntity validate : validates) {
                this.validates.remove(validate);
            }
            this.validates.addAll(validates);
        }

        protected void setError(BaseException error) {
            this.error = error;
        }
    }

    /**
     * 普通请求构建器
     *
     * @param <W>
     */
    public static class RestResultBuilder<W> extends ResultBuilder<W> {

        private W data;
        private boolean isOk;


        public RestResultBuilder<W> withIsOk(boolean isOk) {
            this.isOk = isOk;
            return this;
        }

        public RestResultBuilder<W> withData(W data) {
            this.data = data;
            return this;
        }

        public RestResultBuilder<W> withStatus(StatusEnum status) {
            super.setStatus(status);
            return this;
        }

        public RestResultBuilder<W> withValidate(ValidateEntity validate) {
            super.setValidate(validate);
            return this;
        }

        public RestResultBuilder<W> withValidates(List<ValidateEntity> validates) {
            super.setValidates(validates);
            return this;
        }

        public RestResultBuilder<W> withError(BaseException error) {
            super.setError(error);
            return this;
        }


        public RestResult<W> build() {
            return new RestResult<>(this.status, this.validates, this.error, new RestResultEntity<>(this.isOk, this.data));
        }
    }

    /**
     * 分页构建器
     *
     * @param <W>
     */
    public static class PageResultBuilder<W> extends ResultBuilder<W> {
        private long total;
        private List<W> rows;

        public PageResultBuilder<W> withTotal(long total) {
            this.total = total;
            return this;
        }

        public PageResultBuilder<W> withRows(List<W> rows) {
            this.rows = rows;
            return this;
        }

        public PageResultBuilder<W> withValidate(ValidateEntity validate) {
            super.setValidate(validate);
            return this;
        }

        public PageResultBuilder<W> withValidates(List<ValidateEntity> validates) {
            super.setValidates(validates);
            return this;
        }

        public PageResultBuilder<W> withError(BaseException error) {
            super.setError(error);
            return this;
        }

        public PageResultBuilder<W> withStatus(StatusEnum status) {
            super.setStatus(status);
            return this;
        }

        public RestResult<W> build() {
            return new RestResult<>(this.status, this.validates, this.error, new PageResultEntity<>(this.total, this.rows));
        }
    }
}
