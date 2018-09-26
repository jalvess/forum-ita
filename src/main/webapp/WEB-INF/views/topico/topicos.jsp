<!DOCTYPE html>
<html lang="pt-BR">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/componente/header.jspf"%>
<body id="table-screen">
	<%@ include file="/WEB-INF/componente/header-logo.jspf"%>
	<main>
	<h2>${title}</h2>
	<section id="links">
		<a href="topico?novo">Novo t&oacute;pico</a> <a href="ranking">Ranking</a>
	</section>
	<table>
		<thead>
			<tr>
				<th>T&iacute;tulo</th>
				<th>Usu&aacute;rio</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="topico" varStatus="" items="${topicos}">
				<tr>
					<td><a href="topico?id=${topico.id}" style="font-family:'serif';"> ${topico.titulo} </a></td>
					<td>${topico.usuarioLogin}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>

</html>