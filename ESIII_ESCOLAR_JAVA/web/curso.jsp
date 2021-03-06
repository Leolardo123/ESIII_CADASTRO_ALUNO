<%@page import="Dominio.EntidadeDominio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dominio.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE jsp>
<jsp lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gerenciamento de Cursos</title>
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
                    <h1>Cursos</h1>
                </div>
            </div>
            <div class="row table-responsive">
                <div class="col-sm-12">
                    <div class="d-flex justify-content-end align-items-center my-2">
                        <a class="btn btn-p mx-2" href="./salvar_curso.jsp">Novo Registro</a>
                    </div>
                </div>
                <table class="col-sm-12 table" id="cursos-table">
                    <thead>
                        <tr class="text-start">
                            <th>Id</th>
                            <th>Nome</th>
                            <th>Nivel</th>
                            <th>Dura????o</th>
                            <th>Mensalidade</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>    
                    </tbody>
                </table>
                <div class="modal fade" id="ExcluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Remover Materia</h5>
                            </div>
                            <div class="modal-body">
                                {!}Se voc?? continuar todas as grades do curso ser??o apagadas!
                                <br>{!}Alunos deste curso tamb??m ser??o deletados!
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <a class="btn btn-d" id="modal-exluir-class-id">continuar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./js/modal_handler.js"></script>
        <script src="js/controler_async_request.js"></script>
        <script>
            window.addEventListener('load', function () {
                var cbfun = function(cursos){
                    cursos = JSON.parse(cursos);
                    var table_item = "";
                    for (const [chave, curso] of Object.entries(cursos)) {
                        table_item   +=  '<tr>'+
                                            '<td class="text-start" >'+curso['id']+'</td>'+
                                            '<td class="text-start" >'+curso['nome']+'</td>'+
                                            '<td class="text-start" >'+curso['nivel']+'</td>'+
                                            '<td class="text-start" >'+curso['duracao']+'</td>'+
                                            '<td class="text-start" >'+curso['mensalidade']+'</td>'+
                                            '<td class="text-end" colspan="2">'+
                                                '<a class="btn btn-p" href="./FormEditarCurso?operacao=CONSULTARID&id='+curso['id']+'">Editar</a>'+
                                                '<button type="button" onClick="ExcluirModal(3,['+curso['id']+'])" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#ExcluirModal">Excluir</button>'+
                                            '</td>'+
                                          '</tr>'
                    }
                    document.querySelector('#cursos-table > tbody').innerHTML = table_item;
                }
                getClasses('curso',cbfun);
            });
        </script>
    </body>
</jsp>
    