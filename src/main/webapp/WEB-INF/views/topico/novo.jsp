<!DOCTYPE html>
<html lang="pt-BR">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/componente/header.jspf"%>
<body id="novo-screen">
	<%@ include file="/WEB-INF/componente/header-logo.jspf"%>
	<main>
	<h2>${title}</h2>
	<section id="links">
		<a href="topico">Voltar para T&oacute;picos</a>
	</section>
	<form action="topico" method="post" id="form-topico">
		<label for="titulo">T&iacute;tulo</label> <input id="titulo"
			name="titulo" placeholder="t&iacute;tulo do t&oacute;pico"
			maxlength="100" required /> <label for="conteudo">Conte&uacute;do</label>
		<textarea id="conteudo" name="conteudo" class="required" required></textarea>
		<input type="submit" class="btn" id="btn-enviar">
	</form>
	</main>
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
</body>

</html>