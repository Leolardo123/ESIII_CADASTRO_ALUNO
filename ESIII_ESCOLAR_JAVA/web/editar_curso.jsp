<%@page import="java.util.List"%>
<%@page import="Dominio.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE jsp>
<jsp lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>

    <%@include file="./componentes/header.jsp" %>
    
    <div class="container my-5">

        <!-- FormulÃ¡rio -->
        <form class="row" action="EditarCurso" method="post">
            
            <%
                List<Curso> cursos = (List<Curso>)request.getAttribute("curso");  
            %>

            <!-- Curso -->
            <div class="col-sm-6 my-2"><input class="form-control" type="text" value="<%=cursos.get(0).getNome()%>" name="nome_curso" placeholder="Nome do Curso" required disabled></div>
            <div class="col-sm-2 my-2"><input class="form-control" type="number" value="<%=cursos.get(0).getDuracao()%>" min="1" max="9999" step="1,0" pattern="[0-9]*" name="duracao" placeholder="Duração" required></div>
            <div class="col-sm-2 my-2"><input class="form-control" type="number" value="<%=cursos.get(0).getMensalidade()%>" pattern="[0-9]+([\.,][0-9]+)?" step="0.01" min="0.00" name="mensalidade" placeholder="Mensalidade" required></div>
            <div class="col-sm-2 my-2">
                <select class="form-select" name="nivel">
                    <option value="<%=cursos.get(0).getNivel()%>"><%=cursos.get(0).getNivel()%></option>
                    <%
                        String niveis[] = {"Tecnologo","Bacharelado","Doutorado","Licenciatura","Graduação","Pós-Graduação","Mestrado"} ;
                        for(String nivel : niveis){
                            if(!(cursos.get(0).getNivel().equals(nivel))){
                    %>
                                <option value="<%=nivel%>"><%=nivel%></option>
                    <%      }
                        }
                    %>
                </select>
            </div>
            <div class="col-sm-12"><textarea class="form-control" name="descricao" cols="30" rows="10" placeholder="Descrição" required><%=cursos.get(0).getDescricao()%></textarea></div>
            <!-- Curso -->

            <div class="col-sm-12">
                <input type="hidden" name="id" value="<%=cursos.get(0).getId()%>" >
                <input type="hidden" name="operacao" value="ALTERAR" >
                <button type="submit" class="btn btn-p p-2 m-2">Enviar</button>
                <a class="btn btn-s p-2 m-2" href="./curso.jsp">Voltar</a>
            </div>
        </form>
        <!-- FormulÃ¡rio -->
    </div>
</body>
</jsp>