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

        

            <!-- FormulÃ¡rio -->
            <form class="row justify-content-center align-items-center" action="">

                <div class="row">
                    <div class="col-sm-9">
                        <select class="form-select" name="curso">
                            <option value="1">Analise e desenvolvimento de Sistemas</option>
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
                                    <th colspan="2">Manhã</th>
                                    <th colspan="2">Tarde</th>
                                    <th colspan="2">Noite</th>
                                </tr>

                                <!-- Turnos -->
                                <tr class="text-center">
                                    <th>Turno 1</th>
                                    <th>Turno 2</th>
                                    <th>Turno 1</th>
                                    <th>Turno 2</th>
                                    <th>Turno 1</th>
                                    <th>Turno 2</th>
                                </tr>
                                <!-- Turnos -->

                            </thead>
                            <tbody>
                                
                                <% String[] dias_da_semana = {"Segunda-Feira","Terça-Feira","Quarta-Feira","Quinta-Feira","Sexta-Feira","Sabado"}; %>
                                <% for(int i = 0; i < dias_da_semana.length;i++) {%>
                                    <tr>
                                        <td class="grade-form-td-title"><%=dias_da_semana[i]%></td>
                                        <td class="grade-form-td-values">
                                            <select class="form-select" name="materias[]" >
                                                <option value="1">Administração</option>
                                            </select>
                                            <select class="form-select" name="professores[]" >
                                                <option value="PR0001">Takeshi</option>
                                            </select>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="obrigatorio[]" >
                                                <label class="form-check-label" >Obrigatório</label>
                                            </div>
                                        </td>
                                    <% for(int j = 0; j < 5;j++) {%>
                                    <td class="grade-form-td-values">
                                            <select class="form-select" name="materias[]" >
                                                <option value="1">Administração</option>
                                            </select>
                                            <select class="form-select" name="professores[]" >
                                                <option value="PR0001">Takeshi</option>
                                            </select>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="obrigatorio[]" >
                                                <label class="form-check-label" >Obrigatório</label>
                                            </div>
                                        </td>
                                    <%}%>
                                    </tr>
                                <%}%>
                                


                                
                                <!-- Sabado -->
                                <!-- Dia da semana -->
                                <!-- Manhã -->
                            </tbody>
                        </table>
                    </div>

                    <div class="col-sm-12">
                        <button class="btn btn-primary p-2 m-2">Enviar</button>
                        <a class="btn btn-secondary p-2 m-2" href="./curso.jsp">Voltar</a>
                    </div>
            </form>
            <!-- FormulÃ¡rio -->

        </div>
        

        <%@include file="./componentes/footer.jsp" %>
    </body>
</jsp>