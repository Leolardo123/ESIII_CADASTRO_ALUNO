<!DOCTYPE jsp>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FormulÃ¡rio Professor</title>
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
        <form class="row justify-content-center align-items-center" action="SalvarProfessor" method="post">
            
            <%
                String paises[] = {"AC","AL","AP","AM","AM","BA","CE","DF","ES",
                    "AC","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ",
                    "RN","RS","RO","RR","SC","SP","SE","TO"};
            %>
            
            <!-- Dados Cadastrais Pessoa -->
            <div class="row my-3">
                <div class="col-sm-5 my-2"><input type="text" class="form-control col-sm-6" name="primeiro_nome" placeholder="Primeiro Nome" required></div>
                <div class="col-sm-4 my-2"><input type="text" class="form-control col-sm-6" name="ultimo_nome" placeholder="Ultimo Nome" required></div>
                <div class="col-sm-3 my-2"><input type="date" class="form-control" name="data_nascimento" required></div>
                <div class="col-sm-5 my-2"><input type="text" pattern="[0-9]+" maxlength="9" class="form-control" name="rg" placeholder="RG" required></div>
                <div class="col-sm-7 my-2"><input type="text" pattern="[0-9]+" maxlength="11" class="form-control" name="cpf" placeholder="CPF" required></div>
                <div class="col-sm-5 my-2"><input type="email" class="form-control" name="email" placeholder="E-mail" required></div>
                <div class="col-sm-7 my-2"><input type="number" pattern="/^\d+\.\d{2,2}$/" step="0.01" min="0.00" class="form-control" name="salario" placeholder="Salário" required></div>
            </div>
            <!-- Dados Cadastrais Pessoa -->

            <!-- Dados Cadastrais EndereÃ§o -->
            <div class="row my-3">
                <div class="col-sm-5 my-2"><input type="text" class="form-control" name="logradouro" placeholder="Logradouro" required></div>
                <div class="col-sm-2 my-2"><input type="number" min="1" max="999999" class="form-control" name="numero" placeholder="Numero" required></div>
                <div class="col-sm-5 my-2"><input type="text" class="form-control" name="complemento" placeholder="Complemento"></div>
                <div class="col-sm-5"><input type="text" class="form-control" name="cidade" placeholder="Cidade" required></div>
                <div class="col-sm-4">
                    <select class="form-select" name="estado" required>
                        <% for(String pais : paises){%>
                        <option value="<%=pais%>"><%=pais%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-sm-3"><input type="text" pattern="[0-9]+" maxlength="8" class="form-control" name="cep" placeholder="CEP" required></div>
            </div>
            <!-- Dados Cadastrais EndereÃ§o -->

            <!-- BotÃ£o de Envio -->
            <div class="text-center">
                <input type="hidden" name="operacao" value="SALVAR" >
                <button class="col-sm-2 btn btn-p" type="submit">Enviar</button>
                <a class="col-sm-2 btn btn-s" href="./ListarProfessor?operacao=CONSULTAR">Voltar</a>
            </div>
             <!-- BotÃ£o de Envio -->

        </form>
        <!-- FormulÃ¡rio -->
        
    </div>
</body>
</jsp>