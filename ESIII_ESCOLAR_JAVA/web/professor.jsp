<%@page import="Dominio.Professor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE jsp>
<jsp lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gerenciamento de Professores</title>
        <link rel="stylesheet" href="css/style.css">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script>
            <%
                String msg;
                if (request.getAttribute("msg_success") != null) {
                    msg = (String) request.getAttribute("msg_success");%>
            alert("<%=msg%>");
            <%}%>
        </script>
    </head>
    <body>
        <%@include file="./componentes/header.jsp" %>
        <%@include file="./componentes/modalErrorMsg.jsp" %>
        <div class="container my-5">
            <div class="row">
                <div class="col-sm-12">
                    <h1>Professores</h1>
                </div>
            </div>
            <div class="row table-responsive">
                <div class="col-sm-12">
                    <div class="d-flex justify-content-end align-items-center my-2">
                        <a class="btn btn-p mx-2" href="./salvar_professor.jsp">Novo Registro</a>
                    </div>
                </div>
                <table class="col-sm-12 table" id="professores-table">
                    <thead>
                        <tr class="text-start">
                            <th>Matricula</th>
                            <th>Primeiro Nome</th>
                            <th>Ultimo Nome</th>
                            <th>RG</th>
                            <th>CPF</th>
                            <th>E-mail</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal fade" id="ExcluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Remover Materia</h5>
                    </div>
                    <div class="modal-body">
                        {!}Excluir um professor permite que um aluno com mesmo cpf e rg possa ser cadastrado!
                        <br>{?}Tem certeza que deseja excluir o professor?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <a class="btn btn-d" id="modal-exluir-class-id">Sim</a> 
                    </div>
                </div>
            </div>
        </div>
        <script src="./js/modal_handler.js"></script>
        <script src="js/controler_async_request.js"></script>
        <script>
            window.addEventListener('load', function () {
                var table_item = "";
                var cbfun = function (professores) {
                    professores = JSON.parse(professores);
                    var table_item = "";
                    for (const [chave, professor] of Object.entries(professores)) {
                        table_item += '<tr>' +
                                '<td class="text-start" >' + professor['id'] + '</td>' +
                                '<td class="text-start" >' + professor['pnome'] + '</td>' +
                                '<td class="text-start" >' + professor['unome'] + '</td>' +
                                '<td class="text-start" >' + professor['rg'] + '</td>' +
                                '<td class="text-start" >' + professor['cpf'] + '</td>' +
                                '<td class="text-start" >' + professor['email'] + '</td>' +
                                '<td class="text-end" colspan="2">' +
                                '<a class="btn btn-p" href="./FormEditarProfessor?operacao=CONSULTARID&id=' + professor['id'] + '">Editar</a>' +
                                '<button type="button" onClick="ExcluirModal(2,['+professor['id']+","+professor['endereco']['id']+'])" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#ExcluirModal">Excluir</button>' +
                                '</td>' +
                                '</tr>'
                    }
                    document.querySelector('#professores-table > tbody').innerHTML = table_item;
                }
                getClasses('professor', cbfun);
            });
        </script>
    </body>
</jsp>