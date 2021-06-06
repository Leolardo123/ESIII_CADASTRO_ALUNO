<%
    if (request.getAttribute("msg_error") != null) {
        String msgErr = (String) request.getAttribute("msg_error");
        String[] parts = msgErr.replace(msgErr.substring(0, msgErr.indexOf(":") + 1), "").split("!");%>
        <div class="modal fade show" style="display: block; padding-right: 19px;" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><%=msgErr.substring(0, msgErr.indexOf(":"))%></h5>
                        <button type="button" class="closeErrMsg" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <%
                            for (String part : parts) {
                        %><p><%=part%></p><%
                            }
                        %>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="closeErrMsg btn btn-secondary" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
<%}%>
<script src="./js/modal_handler.js"></script>
