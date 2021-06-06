<%@page import="Dominio.Materia"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE jsp>
<jsp lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FormulÃ¡rio MatÃ©ria</title>
        <link rel="stylesheet" href="css/style.css">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file = "./componentes/header.jsp"%>
        <%Materia materias = (Materia)request.getAttribute("materia");%>
        <div class="container my-5">

            <!-- FormulÃ¡rio -->
            <form class="row" action="SalvarMateria" method="post">

                <div class="col-sm-6 my-2"><input class="form-control" type="text" name="nome_materia" placeholder="Nome da Matéria" required></div>
                <div class="col-sm-6 my-2"><input class="form-control" type="number" min="1" max="999999" pattern="\d*" name="carga_horaria" placeholder="Carga Horária" required></div>
                <div class="dinamic_field">
                    <tr>
                    <div class="col-sm-6 my-2">Dependencias:</div>
                    <div class="col-sm-6 my-2"><span type="button" class="addDep btn btn-success">Add</span></div>
                    <div class="msg-warning-deps"></div>
                    </tr>
                    <div class="dinamic_item" id="deps">
                        <div class="dep-item">
                            <hr>
                            <div class="input-group mb-3">
                                for()
                                <select onchange="" class="form-control dep-item-select" name="dependencia[]">
                                    <option value="0">-</option>
                                    <%for (Materia materia : materias) {%><option value="<%=materia.getId()%>"><%=materia.getNome()%></option><%}%>
                                </select>
                                <div class="input-group-prepend">
                                    <span type="button" class="rmDep btn btn-danger">Delete</span>
                                </div>
                            </div>
                            <div class="subdeps input-group mb-3">
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">Descricão<hr><br><textarea class="form-control" name="descricao" cols="30" rows="10" placeholder="Descrição" required></textarea></div>
                <div class="col-sm-12">
                    <input type="hidden" name="operacao" value="SALVAR" >
                    <button type="submit" class="btn btn-p p-2 m-2">Enviar</button>
                    <a class="btn btn-s p-2 m-2" href="./ListarMateria?operacao=CONSULTAR">Voltar</a>
                </div>
            </form>
            <!-- Formulário -->
        </div>
        <!-- extensões Javascript + jsp -->
        <%@include file="./componentes/materiaFormHandler.jsp" %>
    </body>
</jsp>