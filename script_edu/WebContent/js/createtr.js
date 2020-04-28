//table요소의 tr과td요소를 생성하는 하나의 행을 만드는 기능
//파라미터 타입:배열, 반환타입은 Element 
function creatTr(arrayVal){
	var tr=document.createElement("tr");//<tr><td>text</td><td>text</td><td>text</td><td>text</td></tr>
	for (var i = 0; i < arrayVal.length; i++) {
		var td=document.createElement("td");//<td></td>
		td.textContent=arrayVal[i];//<td>text</td>
		tr.appendChild(td);
	}
	return tr;
}