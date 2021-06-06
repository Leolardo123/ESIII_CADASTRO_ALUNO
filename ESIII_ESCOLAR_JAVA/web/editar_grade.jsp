<%@page import="Dao.DAOMateria"%>
<%@page import="Dao.DAOCurso"%>
<%@page import="Dominio.EntidadeDominio"%>
<%@page import="Dominio.Professor"%>
<%@page import="Dominio.Materia"%>
<%@page import="Dominio.Curso"%>
<%@page import="java.util.List"%>
<%@page import="Dao.DAOProfessor"%>
<%@page import="regrasNegocio.implRegras.ValidarItemGrade"%>
<%@page import="regrasNegocio.implRegras.ValidarGrade"%>
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
    <% 
        GradeCurso grade = request.getParameter("grade");
        
        ValidarItemGrade vG = new ValidarItemGrade();

        DAOProfessor daoPro = new DAOProfessor();
        List<EntidadeDominio> professores = daoPro.consultar(null);

        DAOMateria daoMat = new DAOMateria();
        List<EntidadeDominio> materias = daoMat.consultar(null);

        DAOCurso daoCur = new DAOCurso();
        List<EntidadeDominio> cursos = daoCur.consultar(null);

    %>
    <body>
        <%@include file="./componentes/header.jsp" %>



        <!-- Formulário -->
        <form class="row justify-content-center align-items-center" action="">

            <div class="row">
                <div class="col-sm-9">
                    <select class="form-select" name="curso">
                        <%for (EntidadeDominio entidade : cursos) {
                                Curso curso = (Curso) entidade;
                        %>
                        <option value="<%=curso.getId()%>"><%=curso.getNome()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-sm-3"><input type="number" min="1" max="999" pattern="\d*" class="form-control" name="semestre" placeholder="Semestre"></div>
            </div>

            <div class="table-responsive">
                <div class="table-responsive">
                    <table class="col-sm-12 table my-2">
                        <thead class="grade-form-thead">

                            <!-- Periodos -->
                            <tr class="text-center">
                                <th rowspan="2"></th>
                                    <%for (int l = 1; l < vG.periodos_validos.length; l++) {%>
                                <th colspan="2"><%=vG.periodos_validos[l]%></th>
                                    <%}%>
                            </tr>

                            <!-- Turnos -->
                            <tr class="text-center">
                                <%for (int l = 1; l < vG.periodos_validos.length; l++) {%>
                                <%for (int q = 1; q < vG.turnos_validos.length; q++) {%>
                                <th><%=vG.turnos_validos[q]%></th>
                                    <%}%>
                                    <%}%>
                            </tr>
                            <!-- Turnos -->

                        </thead>
                        <tbody>

                            <%
                                int count = 0;
                                for (int i = 1; i < vG.dias_validos.length; i++) {%>
                            <tr>
                                <td class="grade-form-td-title"><%=vG.dias_validos[i]%></td>
                                <% for (int j = 1; j < vG.periodos_validos.length; j++) {%>
                                <% for (int k = 1; k < vG.turnos_validos.length; k++) {%>
                                <td class="grade-form-td-values">
                                    <select class="form-select" name="materias[<%=count%>]" >
                                        <option value="">-</option>
                                        <%for (EntidadeDominio entidade : materias) {
                                                Materia materia = (Materia) entidade;
                                        %>
                                        <option value="<%=materia.getId()%>"><%=materia.getNome()%></option>
                                        <%}%>
                                    </select>
                                    <select class="form-select" name="professores[<%=count%>]" >
                                        <%for (EntidadeDominio entidade : professores) {
                                                Professor professor = (Professor) entidade;
                                        %>
                                        <option value="<%=professor.getId()%>"><%=professor.getNome()%></option>
                                        <%}%>
                                    </select>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="obrigatorio[<%=count%>]" >
                                        <label class="form-check-label" >Obrigat�rio</label>
                                    </div>
                                </td>
                                <%count++;}%>
                                <%}%>
                            </tr>
                            <%}%>

                        </tbody>
                    </table>
                </div>

                <div class="col-sm-12">
                    <button class="btn btn-primary p-2 m-2">Enviar</button>
                    <a class="btn btn-secondary p-2 m-2" href="./curso.jsp">Voltar</a>
                </div>
        </form>
        <!-- Formulário -->

        </div>


        <%@include file="./componentes/footer.jsp" %>
    </body>
</jsp>