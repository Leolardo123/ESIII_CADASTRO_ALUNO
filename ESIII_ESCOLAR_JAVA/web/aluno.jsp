<!DOCTYPE jsp>
<jsp lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciamento de Alunos</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="./componentes/header.jsp" %>

    <div class="container my-5">
        <div class="row table-responsive">
            <div class="col-sm-12">
                <div class="d-flex justify-content-end align-items-center my-2">
                    <a class="btn btn-primary mx-2" href="./salvar_aluno.jsp">Novo Registro</a>
                    <a class="btn btn-danger" href="#">Deletar Tudo</a>
                </div>
            </div>
            <table class="col-sm-12 table">
                <thead>
                    <tr class="text-center">
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
                    <tr>
                        <td class="text-center" id="AL0001" >AL0001</td>
                        <td class="text-center" >Akira</td>
                        <td class="text-center" >Fukamizu</td>
                        <td class="text-center" >33.345.055-7</td>
                        <td class="text-center" >333.345.055-11</td>
                        <td class="text-center" >akira.pitu@gmail.com</td>
                        <td><a class="btn btn-primary" href="">Editar</a></td>
                        <td><a class="btn btn-danger" href="">Remover</a></td>
                    </tr>
                    <tr>
                        <td class="text-center" id="AL0001" >AL0001</td>
                        <td class="text-center" >Akira</td>
                        <td class="text-center" >Fukamizu</td>
                        <td class="text-center" >33.345.055-7</td>
                        <td class="text-center" >333.345.055-11</td>
                        <td class="text-center" >akira.pitu@gmail.com</td>
                        <td><a class="btn btn-primary" href="">Editar</a></td>
                        <td><a class="btn btn-danger" href="">Remover</a></td>
                    </tr>
                    <tr>
                        <td class="text-center" id="AL0001" >AL0001</td>
                        <td class="text-center" >Akira</td>
                        <td class="text-center" >Fukamizu</td>
                        <td class="text-center" >33.345.055-7</td>
                        <td class="text-center" >333.345.055-11</td>
                        <td class="text-center" >akira.pitu@gmail.com</td>
                        <td><a class="btn btn-primary" href="">Editar</a></td>
                        <td><a class="btn btn-danger" href="">Remover</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <%@include file="./componentes/footer.jsp" %>

</body>
</jsp>