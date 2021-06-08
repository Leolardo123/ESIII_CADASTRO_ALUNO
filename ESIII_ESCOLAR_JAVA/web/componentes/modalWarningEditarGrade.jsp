<%-- 
    Document   : modalWarningEditarGrade
    Created on : 07/06/2021, 20:45:24
    Author     : 55119
--%>

<div class="modal fade show" style="display: block; padding-right: 19px;" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="modalWarningEditarGrade" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalWarningEditarLabel">Aviso</h5>
                <button type="button" class="closeErrMsg" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body-warning-editar">
                   {!}O Curso não pode ser alterado!
            </div>
            <div class="modal-footer">
                <button type="button" class="closeErrMsg btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="closeErrMsg btn btn-secondary" data-dismiss="modal">Sim</button>
            </div>
        </div>
    </div>
</div>
