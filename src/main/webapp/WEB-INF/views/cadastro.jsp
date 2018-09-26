<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/componente/header.jspf"%>

<script type="text/javascript">
	window.onload = function() {
		verificaCampoVazio();
		var btn = document.querySelector('[type=submit]');
		btn.addEventListener('click', verificaCampoVazio);
	}

	function verificaCampoVazio() {
		var campos = document.querySelectorAll('[required]');
		Object.values(campos).forEach(function(a) {
			a.value = a.value.trim();
		});
	}
</script>
<body id="cadastro-screen">
	<%@ include file="/WEB-INF/componente/header-logo.jspf"%>
	<main>
	<h2>${title}</h2>
	<form action="" id="form-cadastro" method="post">
		<label for="nome">Nome</label> <input type="text" name="nome"
			id="nome" placeholder="nome" maxlength="50" required /> <label
			for="login">Login</label> <input type="text" name="login" id="login"
			placeholder="login" maxlength="10" required /> <label for="email">Email</label>
		<input type="email" name="email" id="email" placeholder="email"
			required /> <label for="senha">Senha</label> <input type="password"
			name="senha" id="senha" placeholder="senha" required /> <input
			type="submit" class="btn" />
	</form>
	</main>

</body>

</html>