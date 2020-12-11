package com.spring.boot.mybatisplus.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class CustomInnerInterceptor extends JsqlParserSupport implements InnerInterceptor {

    @Override
    public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        log.debug("willDoQuery ================================================");
        String mapperName = new String(ms.getResource().getBytes(StandardCharsets.UTF_8));
        String mapperId = ms.getId();
        String sqlCommandType = ms.getSqlCommandType().name();
        String executeSql = new String(boundSql.getSql().getBytes(StandardCharsets.UTF_8));
        log.debug("\n===mapperName: {}\n===mapperId: {}\n===sqlCommandType: {}\n===executeSql: {}",
                mapperName, mapperId, sqlCommandType, executeSql);

        if (parameter instanceof Map) {
            Map<?, ?> parameterMap = (Map<?, ?>) parameter;
            for (Map.Entry entry : parameterMap.entrySet()) {
                if (entry.getValue() != null && entry.getValue() instanceof LambdaQueryWrapper) {
                    LambdaQueryWrapper lambdaQueryWrapper = (LambdaQueryWrapper) entry.getValue();
                    Map<String, Object> paramNameValuePairs = lambdaQueryWrapper.getParamNameValuePairs();
                    for (Map.Entry<String, Object> entry2 : paramNameValuePairs.entrySet()) {
                        log.debug("\n===parameter name: {} value: {}", entry2.getKey(), entry2.getValue());
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        log.debug("beforeQuery ================================================");
    }

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        log.debug("willDoUpdate ================================================");
        return false;
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        log.debug("beforeUpdate ================================================");
    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        log.debug("beforePrepare ================================================");
    }

    @Override
    public void setProperties(Properties properties) {
        log.debug("setProperties ================================================");
    }

    @Override
    protected void processInsert(Insert insert, int index, Object obj) {
        super.processInsert(insert, index, obj);
        log.debug("processInsert ================================================");
    }

    @Override
    protected void processDelete(Delete delete, int index, Object obj) {
        super.processDelete(delete, index, obj);
        log.debug("processDelete ================================================");
    }

    @Override
    protected void processUpdate(Update update, int index, Object obj) {
        super.processUpdate(update, index, obj);
        log.debug("processUpdate ================================================");
    }

    @Override
    protected void processSelect(Select select, int index, Object obj) {
        super.processSelect(select, index, obj);
        log.debug("processSelect ================================================");
    }
}
