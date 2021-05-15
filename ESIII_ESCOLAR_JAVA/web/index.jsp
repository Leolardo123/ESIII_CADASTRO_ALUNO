<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>
    <div id="header"></div>

    <div class="container my-5">

        <!-- Formulário -->
        <form class="row" action="SalvarCurso" method="get">

            <!-- Curso -->
            <div class="col-sm-6 my-2"><input class="form-control" type="text" name="nome_curso" placeholder="Nome do Curso" required></div>
            <div class="col-sm-2 my-2"><input class="form-control" type="number" name="duracao" placeholder="Duração" required></div>
            <div class="col-sm-2 my-2"><input class="form-control" type="number" name="mensalidade" placeholder="Mensalidade" required></div>
            <div class="col-sm-2 my-2">
                <select class="form-select" name="nivel">
                    <option value="Tecnologo">Tecnologo</option>
                    <option value="Bacharelado">Bacharelado</option>
                    <option value="Doutorado">Doutorado</option>
                </select>
            </div>
            <div class="col-sm-12"><textarea class="form-control" name="descricao" cols="30" rows="10" placeholder="Descrição" required></textarea></div>
            <!-- Curso -->

            <div class="col-sm-12">
                <input type="hidden" name="operacao" value="SALVAR" >
                <button type="submit" class="btn btn-primary p-2 m-2">Enviar</button>
                <a class="btn btn-secondary p-2 m-2" href="./curso.html">Voltar</a>
            </div>
        </form>
        <!-- Formulário -->

    </div>

    <div id="footer"></div>

    <script src="./js/header.js"></script>
    <script src="./js/footer.js"></script>
</body>
</html>