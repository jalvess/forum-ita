<!DOCTYPE html>
<html lang="pt-BR">
<%@ include file="/WEB-INF/componente/header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body id="table-screen">
	<%@ include file="/WEB-INF/componente/header-logo.jspf"%>
	<main>
	<h2>${title}</h2>
	<section id="links">
		<a href="topico">T&oacute;picos</a>
	</section>
	<table>
		<thead>
			<tr>
				<th>Posi&ccedil;&atilde;o</th>
				<th>Nome</th>
				<th>Usu&aacute;rio</th>
				<th>Pontos</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usuario" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.login}</td>
					<td>${usuario.pontos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</main>
</body>

</html>