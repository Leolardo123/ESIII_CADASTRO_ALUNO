<%@page import="java.util.HashMap"%>
<%@page import="Dominio.ItemGrade"%>
<%@page import="Dominio.GradeCurso"%>
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
        List<GradeCurso> grades = (List<GradeCurso>) request.getAttribute("grade");
        GradeCurso grade = grades.get(0);

        List<ItemGrade> itensGrade = grade.getItens();

        ValidarItemGrade vG = new ValidarItemGrade();

        HashMap<String, ItemGrade> gradeDeItens = new HashMap<String, ItemGrade>();
        //formato da chave: turno-periodo-diaSemana

        for (ItemGrade item : itensGrade) {
            String chave = item.getChave();
            gradeDeItens.put(chave, item);
        }

        DAOProfessor daoPro = new DAOProfessor();
        List<EntidadeDominio> professores = daoPro.consultar(null);

        DAOMateria daoMat = new DAOMateria();
        List<EntidadeDominio> materias = daoMat.consultar(null);

        DAOCurso daoCur = new DAOCurso();
        List<EntidadeDominio> cursos = daoCur.consultar(grade);

        Curso cursoGrade = new Curso();
        if (cursos != null && cursos.size() > 0) {
            cursoGrade = (Curso) cursos.get(0);
        }
    %>
    <body>
        <%@include file="./componentes/header.jsp" %>
        <%@include file="./componentes/modalErrorMsg.jsp" %>

        <!-- Formulário -->
        <form class="row justify-content-center align-items-center" action="EditarGradeCurso">
            <div class="row">
                <div class="col-sm-9">
                    <select class="form-select" name="curso">
                        <option value="<%=cursoGrade.getId()%>"><%=cursoGrade.getNome()%></option>
                    </select>
                </div>
                <div class="col-sm-3">
                    <select class="form-select" name="semestre">

                    </select>
                </div>
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
                        </thead>
                        <tbody>

                            <%
                                int count = 0;
                                for (int i = 1; i < vG.dias_validos.length; i++) {%>
                            <tr>
                                <td class="grade-form-td-title"><%=vG.dias_validos[i]%></td>
                                <% for (int j = 1; j < vG.periodos_validos.length; j++) {%>
                                <% for (int k = 1; k < vG.turnos_validos.length; k++) {
                                        String chave = k + "-" + j + "-" + i;
                                %>
                                <td class="grade-form-td-values" id="<%=chave%>">
                                    <select class="form-select" name="materias[]" >
                                        <%if (gradeDeItens.get(chave) != null) {%>
                                        <option class="opcao-cadastrada" value="<%=gradeDeItens.get(chave).getMateria().getId()%>">
                                            <%=gradeDeItens.get(chave).getMateria().getNome()%></option>
                                            <%}%>
                                        <!-- Op��es Padrao -->    
                                        <option value="-1">-</option>
                                        <%for (EntidadeDominio entidade : materias) {
                                                Materia materia = (Materia) entidade;
                                        %>
                                        <option value="<%=materia.getId()%>"><%=materia.getNome()%></option>
                                        <%}%>
                                    </select>
                                    <select class="form-select" name="professores[]" >
                                        <%if (gradeDeItens.get(chave) != null) {%>
                                        <option class="opcao-cadastrada" value="<%=gradeDeItens.get(chave).getMateria().getId()%>">
                                            <%=gradeDeItens.get(chave).getProfessor().getNome()%></option>
                                            <%}%>
                                        <!-- Op��es Padrao -->   
                                        <option value="-1">-</option>
                                        <%for (EntidadeDominio entidade : professores) {
                                                Professor professor = (Professor) entidade;
                                        %>
                                        <option value="<%=professor.getId()%>"><%=professor.getNome()%></option>
                                        <%}%>
                                    </select>
                                </td>
                                <%count++;
                                    }%>
                                <%}%>
                            </tr>
                            <%}%>

                        </tbody>
                    </table>
                </div>

                <div class="col-sm-12">
                    <input type="hidden" name="id" value="<%=grade.getId()%>" >
                    <input type="hidden" name="old_semestre" value="<%=grade.getSemestre()%>" >
                    <input type="hidden" name="operacao" value="ALTERAR" >
                    <button id="trigger-WarningEditar" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#WarningEditarGrade">Confirmar</button>
                    <a class="btn btn-secondary p-2 m-2" >Voltar</a>
                </div>

                <!-- Valores para referencia do item no Banco de dados -->
                <input type="hidden" min="1" max="999" pattern="\d*" class="form-control" name="id" value="<%=grade.getId()%>">
                <div class="modal fade" id="WarningEditarGrade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Editar Grade</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-p p-2 m-2">Continuar</button>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
        <!-- Formulário -->

        <%@include file="./componentes/gradeFormHandler.jsp"%>
        </div>
    </body>
</jsp>