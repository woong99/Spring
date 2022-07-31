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
        <h1 class="page-header">Board Read</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">Board Read Page</div>
            <div class="panel-body">

                <div class="form-group">
                    <label>Bno</label>
                    <input class="form-control" name="bno" value="<c:out value="${get.bno}"/>" readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Title</label>
                    <input class="form-control" name="title" value="<c:out value="${get.title}"/>"
                           readonly="readonly">
                </div>

                <div class="form-group">
                    <label>Text Area</label>
                    <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out
                            value="${get.content}"/>
                        </textarea>
                </div>

                <div class="form-group">
                    <label>Writer</label>
                    <input class="form-control" name="writer" value="<c:out value="${get.writer}"/> "
                           readonly="readonly">
                </div>

                <button data-oper="modify" class="btn btn-default">
                    Modify
                </button>
                <button data-oper="list" class="btn btn-info">
                    List
                </button>


                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="bno" name="bno" value='<c:out value="${get.bno}"/>'>
                    <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
                    <input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
                    <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
                    <input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">

        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Reply
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
            </div>

            <div class="panel-body">
                <ul class="chat">
                    <li class="left clearfix" data-rno="12">
                        <div>
                            <div class="header">
                                <strong class="primary-font">user00</strong>
                                <small class="pull-right text-muted">2018-01-01 13:13</small>
                            </div>
                            <p>Good job!</p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="panel-footer">

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Reply</label>
                    <input class="form-control" name="reply" value="New Reply!!!!">
                </div>
                <div class="form-group">
                    <label>Replyer</label>
                    <input class="form-control" name="replyer" value="replyer">
                </div>
                <div class="form-group">
                    <label>Reply Date</label>
                    <input class="form-control" name="replyDate" value="">
                </div>
            </div>
            <div class="modal-footer">
                <button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
                <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
                <button id="modalRegisterBtn" type="button" class="btn btn-primary" data-dismiss="modal">Register
                </button>
                <button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        const bnoValue = '<c:out value="${get.bno}"/>';
        const replyUl = $(".chat");

        showList(1);

        function showList(page) {
            console.log("show list " + page);

            replyService.getList({bno: bnoValue, page: page || 1}, function (replyCnt, list) {

                console.log("replyCnt: " + replyCnt);
                console.log("list: " + list);
                console.log(list);

                if (page == -1) {
                    pageNum = Math.ceil(replyCnt / 10.0);
                    showList(pageNum);
                    return;
                }

                let str = "";
                if (list == null || list.length == 0) {
                    replyUl.html("");
                    return;
                }
                for (let i = 0, len = list.length || 0; i < len; i++) {
                    str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
                    str += " <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
                    str += " <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
                    str += " <p>" + list[i].reply + "</p></div></li>";
                }
                replyUl.html(str);
                showReplyPage(replyCnt);
            })
        }

        const modal = $(".modal");
        const modalInputReply = modal.find("input[name='reply']");
        const modalInputReplyer = modal.find("input[name='replyer']");
        const modalInputReplyDate = modal.find("input[name='replyDate']");

        const modalModBtn = $("#modalModBtn");
        const modalRemoveBtn = $("#modalRemoveBtn");
        const modalRegisterBtn = $("#modalRegisterBtn");

        $("#addReplyBtn").on("click", function (e) {
            modal.find("input").val("");
            modalInputReplyDate.closest("div").hide();
            modal.find("button[id != 'modalCloseBtn']").hide();

            modalRegisterBtn.show();

            $(".modal").modal("show");
        })

        $("#modalRegisterBtn").on("click", function (e) {
            const reply = {
                reply: modalInputReply.val(),
                replyer: modalInputReplyer.val(),
                bno: bnoValue
            };
            replyService.add(reply, function (result) {
                alert(result);

                modal.find("input").val("");
                modal.modal("hide");

                showList(-1);
            })
        })

        $(".chat").on("click", "li", function (e) {
            const rno = $(this).data("rno");

            replyService.get(rno, function (reply) {
                modalInputReply.val(reply.reply);
                modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
                modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
                modal.data("rno", reply.rno);

                modal.find("button[id != 'modalCloseBtn']").hide();
                modalModBtn.show();
                modalRemoveBtn.show();

                $(".modal").modal("show");
            })
        })

        $("#modalModBtn").on("click", function (e) {
            const reply = {rno: modal.data("rno"), reply: modalInputReply.val()};

            replyService.update(reply, function (result) {
                alert(result);
                modal.modal("hide");
                showList(pageNum);
            })
        })

        $("#modalRemoveBtn").on("click", function (e) {
            const rno = modal.data("rno");

            replyService.remove(rno, function (result) {
                alert(result);
                modal.modal("hide");
                showList(pageNum);
            })
        })
        let pageNum = 1;
        const replyPageFooter = $(".panel-footer");

        function showReplyPage(replyCnt) {
            let endNum = Math.ceil(pageNum / 10.0) * 10;
            const startNum = endNum - 9;
            console.log(endNum, startNum)
            const prev = startNum != 1;
            let next = false;

            if (endNum * 10 >= replyCnt) {
                endNum = Math.ceil(replyCnt / 10.0)
            }
            if (endNum * 10 < replyCnt) {
                next = true;
            }

            let str = "<ul class='pagination pull-right'>";

            if (prev) {
                str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
            }

            for (let i = startNum; i <= endNum; i++) {
                const active = pageNum == i ? "active" : "";

                str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
            }

            if (next) {
                str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>";
            }

            str += "</ul></div>";

            console.log(str);

            replyPageFooter.html(str);

        }

        replyPageFooter.on("click", "li a", function (e) {
            e.preventDefault();
            console.log("page click");

            const targetPageNum = $(this).attr("href");

            console.log("targetPageNum: " + targetPageNum);

            pageNum = targetPageNum;

            showList(pageNum);
        })
    })


    <%--console.log("==============");--%>
    <%--console.log("JS TEST");--%>

    <%--var bnoValue = '<c:out value="${get.bno}"/>';--%>

    // replyService.add(
    //     {reply: "JS Test", replyer: "tester", bno: bnoValue},
    //     function (result) {
    //         alert("RESULT: " + result);
    //     }
    // )

    // replyService.getList({bno: bnoValue, page: 1}, function (list) {
    //     for (let i = 0, len = list.length || 0; i < len; i++) {
    //         console.log(list[i]);
    //     }
    // })

    // replyService.remove(4, function (count) {
    //     console.log(count);
    //
    //     if (count == "success") {
    //         alert("REMOVED");
    //     }
    // }, function (err) {
    //     alert("ERROR...");
    // })

    // replyService.update({
    //     rno: 8,
    //     bno: bnoValue,
    //     reply: "진짜 수정!!"
    // }, function (result) {
    //     alert("수정 완료...")
    // })

    // replyService.get(10, function (data) {
    //     console.log(data);
    // })
</script>

<script type="text/javascript">
    $(document).ready(function () {
        var operForm = $("#operForm");

        $("button[data-oper='modify']").on("click", function (e) {
            operForm.attr("action", "/board/modify").submit();
        });

        $("button[data-oper='list']").on("click", function (e) {
            operForm.find("#bno").remove();
            operForm.attr("action", "/board/list");
            operForm.submit();
        });
    });
</script>
<%@include file="../include/footer.jsp" %>