package nyc.citymsp.tools.azlogtool.util;

import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import nyc.citymsp.tools.azlogtool.entity.AZLTUser_;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by kervin on 2016-07-07.
 */
public final class AZLTUserSpecifications
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTUserSpecifications.class);

    public static Specification<AZLTUser> runUserFilterQuery(
                                                final String logic,
                                                final String field1,
                                                final String operator1,
                                                final String value1,
                                                final String field2,
                                                final String operator2,
                                                final String value2 )
    {
        return (root, query, cb) ->
        {
            Predicate res = null;

            Predicate expr1 = null;
            Predicate expr2 = null;

            expr1 = evalExpression(cb, root, field1, operator1, value1);
            expr2 = evalExpression(cb, root, field2, operator2, value2);

            if( expr1 == null && expr2 == null )
            {
                LOGGER.warn("No expressions given for evaluation");

            }
            else if( expr1 == null )
            {
                res = expr2;
            }
            else if( expr2 == null )
            {
                res = expr1;
            }
            else
            {
                switch( logic )
                {
                    case "and":
                        res = cb.and(expr1, expr2);
                        break;

                    case "or":
                        res = cb.or(expr1, expr2);
                        break;

                    default:
                        LOGGER.warn(String.format("Invalid logic value '%s'", logic));
                }
            }

            return res;
        };
    }

    public static Predicate evalExpression(final CriteriaBuilder cb,
                                           final Root<AZLTUser> root,
                                           final String field,
                                           final String operator,
                                           final String value)
    {
        Predicate res = null;

        if( StringUtils.isNoneBlank(field)
                && StringUtils.isNoneBlank(operator)
                && StringUtils.isNoneBlank(value))
        {
            switch( operator )
            {
                case "eq":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.equal(root.get(AZLTUser_.username), value);
                            break;

                        case "firstName":
                            res = cb.equal(root.get(AZLTUser_.firstName), value);
                            break;

                        case "lastName":
                            res = cb.equal(root.get(AZLTUser_.lastName), value);
                            break;
                    }
                }
                break;

                case "neq":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.notEqual(root.get(AZLTUser_.username), value);
                            break;

                        case "firstName":
                            res = cb.notEqual(root.get(AZLTUser_.firstName), value);
                            break;

                        case "lastName":
                            res = cb.notEqual(root.get(AZLTUser_.lastName), value);
                            break;
                    }
                }
                break;

                case "contains":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.like(root.get(AZLTUser_.username),
                                    String.format("%%%s%%",value));
                            break;

                        case "firstName":
                            res = cb.like(root.get(AZLTUser_.firstName),
                                    String.format("%%%s%%",value));
                            break;

                        case "lastName":
                            res = cb.like(root.get(AZLTUser_.firstName),
                                    String.format("%%%s%%",value));
                            break;
                    }
                }
                break;

                case "doesnotcontain":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.notLike(root.get(AZLTUser_.username),
                                    String.format("%%%s%%",value));
                            break;

                        case "firstName":
                            res = cb.notLike(root.get(AZLTUser_.firstName),
                                    String.format("%%%s%%",value));
                            break;

                        case "lastName":
                            res = cb.notLike(root.get(AZLTUser_.lastName),
                                    String.format("%%%s%%",value));
                            break;
                    }
                }
                break;

                case "startswith":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.like(root.get(AZLTUser_.username),
                                    String.format("%s%%",value));
                            break;

                        case "firstName":
                            res = cb.like(root.get(AZLTUser_.firstName),
                                    String.format("%s%%",value));
                            break;

                        case "lastName":
                            res = cb.like(root.get(AZLTUser_.lastName),
                                    String.format("%s%%",value));
                            break;
                    }
                }
                break;

                case "endswith":
                {
                    switch( field )
                    {
                        case "username":
                            res = cb.like(root.get(AZLTUser_.username),
                                    String.format("%%%s",value));
                            break;

                        case "firstName":
                            res = cb.like(root.get(AZLTUser_.firstName),
                                    String.format("%%%s",value));
                            break;

                        case "lastName":
                            res = cb.like(root.get(AZLTUser_.firstName),
                                    String.format("%%%s",value));
                            break;
                    }
                }
                break;
            }
        }

        return res;
    }
}
