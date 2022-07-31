<%--
  Created by IntelliJ IDEA.
  User: woong_9yo
  Date: 2022/07/22
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../include/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Modify Page</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Board Modify Page</div>
            <div class="panel-body">

                <form role="form" action="/board/modify" method="post">
                    <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/> ">
                    <input type="hidden" name="amount" value="<c:out value="${cri.amount}"/> ">
                    <input type="hidden" name="type" value="<c:out value="${cri.type}"/> ">
                    <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/> ">

                    <div class="form-group">
                        <label>Bno</label>
                        <input class="form-control" name="bno" value="<c:out value="${get.bno}"/>" readonly="readonly">
                    </div>

                    <div class="form-group">
                        <label>Title</label>
                        <input class="form-control" name="title" value="<c:out value="${get.title}"/>">
                    </div>

                    <div class="form-group">
                        <label>Text Area</label>
                        <textarea class="form-control" rows="3" name="content"><c:out
                                value="${get.content}"/>
                        </textarea>
                    </div>

                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" name="writer" value="<c:out value="${get.writer}"/> "
                               readonly="readonly">
                    </div>

                    <div class="form-group hidden">
                        <label>RegDate</label>
                        <input class="form-control" name="regDate"
                               value='<fmt:formatDate pattern="yyyy/MM/dd" value="${get.regDate}"/>'
                               readonly="readonly">
                    </div>

                    <div class="form-group hidden">
                        <label>Update Date</label>
                        <input class="form-control" name="updateDate"
                               value='<fmt:formatDate pattern="yyyy/MM/dd" value="${get.updateDate}"/>'
                               readonly="readonly">
                    </div>

                    <button type="submit" data-oper="modify" class="btn btn-default">
                        Modify
                    </button>

                    <button type="submit" data-oper="remove" class="btn btn-danger">
                        Remove
                    </button>

                    <button type="submit" data-oper="list" class="btn btn-info">
                        List
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var formObj = $('form');

        $('button').on("click", function (e) {
            e.preventDefault();

            var operation = $(this).data("oper");

            console.log(operation);

            if (operation === 'remove') {
                formObj.attr("action", "/board/remove");
            } else if (operation === 'list') {
                formObj.attr("action", "/board/list").attr("method", "get");
                var pageNumTag = $("input[name='pageNum']").clone();
                var amountTag = $("input[name='amount']").clone();
                var keywordTag = $("input[name='keyword']").clone();
                var typeTag = $("input[name='type']").clone();
                formObj.empty();
                formObj.append(pageNumTag);
                formObj.append(amountTag);
                formObj.append(keywordTag);
                formObj.append(typeTag);
            }
            formObj.submit();
        });
    });
</script>
<%@include file="../include/footer.jsp" %>