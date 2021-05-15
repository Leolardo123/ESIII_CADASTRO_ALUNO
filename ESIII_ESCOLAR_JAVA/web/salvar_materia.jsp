<!DOCTYPE jsp>
<jsp lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário Matéria</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="./componentes/header.jsp" %>

    <div class="container my-5">

        <!-- Formulário -->
        <form class="row" action="">
            <div class="col-sm-5 my-2"><input class="form-control" type="text" name="nome_materia" placeholder="Nome da Mat�ria" required></div>
            <div class="col-sm-3 my-2"><input class="form-control" type="number" min="1" max="999" pattern="\d*" name="carga_horaria" placeholder="Carga Hor�ria" required></div>
            <div class="col-sm-4 my-2">
                <select class="form-select" name="dependencia">
                    <option value="NULL">Sem depend�ncia</option>
                    <option value="ADM">Administra��o</option>
                    <option value="MD">Matem�tica Discreta</option>
                </select>
            </div>
            <div class="col-sm-12"><textarea class="form-control" name="descricao" cols="30" rows="10" placeholder="Descri��o" required></textarea></div>
            <div class="col-sm-12">
                <button class="btn btn-primary p-2 m-2">Enviar</button>
                <a class="btn btn-secondary p-2 m-2" href="./materia.jsp">Voltar</a>
            </div>
        </form>
        <!-- Formulário -->

    </div>

    <%@include file="./componentes/footer.jsp" %>
</body>
</jsp>

