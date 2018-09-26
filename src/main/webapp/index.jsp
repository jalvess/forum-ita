<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="./WEB-INF/componente/header.jspf"%>
<body id="login-screen">
	<header>
		<h1>F&oacute;rum</h1>
		<form action="auth" id="form-login" autocomplete="off" method="post">
			<div class="form-group">
				<label for="login">Login</label> <input type="text" id="login"
					name="login" placeholder="login" />
			</div>
			<div class="form-group">
				<label for="senha">Senha</label> <input type="password" id="senha"
					name="senha" placeholder="senha" />
			</div>
			<input type="submit" id="btn_enviar" value="Logar" />
		</form>
		<span class="msg-erro">${erro}</span> <a href="cadastro"
			id="link-cadastro">N&atilde;o possui cadastro? Cadastre-se</a>
	</header>
</body>
</html>