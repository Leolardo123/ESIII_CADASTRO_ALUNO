<!DOCTYPE jsp>
<jsp lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário Aluno</title>
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
        <form class="row justify-content-center align-items-center" action="SalvarAluno" method="get">
            
            <!-- Dados Cadastrais Pessoa -->
            <div class="row my-3">
                <div class="col-sm-5 my-2"><input type="text" class="form-control col-sm-6" name="primeiro_nome" placeholder="Primeiro Nome" required></div>
                <div class="col-sm-4 my-2"><input type="text" class="form-control col-sm-6" name="ultimo_nome" placeholder="Ultimo Nome" required></div>
                <div class="col-sm-3 my-2"><input type="date" class="form-control" name="data_nascimento" required></div>
                <div class="col-sm-5 my-2"><input type="text" pattern="[0-9]+" maxlength="10" class="form-control" name="rg" placeholder="RG" required></div>
                <div class="col-sm-7 my-2"><input type="text" pattern="[0-9]+" maxlength="11" class="form-control" name="cpf" placeholder="CPF" required></div>
                <div class="col-sm-5 my-2"><input type="email" class="form-control" name="email" placeholder="E-mail" required></div>
                <div class="col-sm-7 my-2">
                    <select class="form-select" name="curso" required>
                        <option value="ADS">Analise e Desenvolvimento Sistemas</option>
                        <option value="RH">Recursos Humanos</option>
                    </select>
                </div>
            </div>
            <!-- Dados Cadastrais Pessoa -->

            <!-- Dados Cadastrais Endereço -->
            <div class="row my-3">
                <div class="col-sm-5 my-2"><input type="text" class="form-control" name="logradouro" placeholder="Logradouro" required></div>
                <div class="col-sm-2 my-2"><input type="number" min="1" max="999999" class="form-control" name="numero" placeholder="Numero" required></div>
                <div class="col-sm-5 my-2"><input type="text" class="form-control" name="complemento" placeholder="Complemento"></div>
                <div class="col-sm-5"><input type="text" class="form-control" name="cidade" placeholder="Cidade" required></div>
                <div class="col-sm-4">
                    <select class="form-select" name="estado" required>
                        <option value="AC">Acre</option>
                        <option value="AL">Alagoas</option>
                        <option value="AP">Amap�</option>
                        <option value="AM">Amazonas</option>
                        <option value="BA">Bahia</option>
                        <option value="CE">Cear�</option>
                        <option value="DF">Distrito Federal</option>
                        <option value="ES">Esp�rito Santo</option>
                        <option value="GO">Goi�s</option>
                        <option value="MA">Maranh�o</option>
                        <option value="MT">Mato Grosso</option>
                        <option value="MS">Mato Grosso do Sul</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="PA">Par�</option>
                        <option value="PB">Para�ba</option>
                        <option value="PR">Paran�</option>
                        <option value="PE">Pernambuco</option>
                        <option value="PI">Piau�</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="RN">Rio Grande do Norte</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="RO">Rond�nia</option>
                        <option value="RR">Roraima</option>
                        <option value="SC">Santa Catarina</option>
                        <option value="SP">S�o Paulo</option>
                        <option value="SE">Sergipe</option>
                        <option value="TO">Tocantins</option>
                    </select>
                </div>
                <div class="col-sm-3"><input type="text" pattern="[0-9]+" maxlength="8" class="form-control" name="cep" placeholder="CEP" required></div>
            </div>
            <!-- Dados Cadastrais Endereço -->

            <!-- Botão de Envio -->
            <div class="text-center">
                <button class="col-sm-2 btn btn-primary" type="submit">Enviar</button>
                <a class="col-sm-2 btn btn-secondary" href="./aluno.jsp">Voltar</a>
            </div>
             <!-- Botão de Envio -->

        </form>
        <!-- Formulário -->
        
    </div>

    <%@include file="./componentes/footer.jsp" %>
</body>
</jsp>