<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
<body id="topico">
	<%@ include file="/WEB-INF/componente/header-logo.jspf"%>

	<main>
	<h2>${topico.titulo}</h2>
	<p style="font-style: italic; margin-right: 0;">Escrito por:
		${topico.usuarioLogin}</p>
	<section id="links">
		<a href="topico">Voltar para T&oacute;picos</a>
	</section>
	<section id="conteudo">
		<p>${topico.conteudo}</p>
	</section>
	<section id="comentario">
		<h3>Comentários</h3>
		<form action="comentario?id_topico=${topico.id}&user=${user}"
			method="post">
			<textarea placeholder="Escrever comentário" name="comentario"
				id="campo-comentario" class="required" required></textarea>
			<input type="submit" value="Enviar comentário" class="btn"
				id="btn-enviar" />
		</form>
		<c:forEach var="comentario" items="${comentarios}" varStatus="status">
			<div style="background-color: #872222; padding: 2%; margin: 1% 0;">
				<p>${comentario.usuarioLogin}</p>
				<p style="font-style: italic;">${comentario.comentario}</p>
			</div>
		</c:forEach>
	</section>
	</main>
</body>

</html>