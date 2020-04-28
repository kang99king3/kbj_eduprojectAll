//1.다중배열: 2차원배열 만들기
function multiArray(){
	var arrlen=3;//길이값
	var arr=new Array(arrlen);//배열선언,정의(길이)
	for (var i = 0; i < arr.length; i++) {
		arr[i]=new Array(arrlen);
	}
	//arr[[ , , ],[ , , ],[ , , ]]//자동초기화 X
//	alert(arr.toString());
	arr[0][0]="apple";
	arr[0][1]="banana";
	arr[0][2]="cherry";
	//arr[["apple" ,"banana" ,"cherry" ],[1 ,2 ,3 ]
	//  ,[[] ,[] ,[] ]]
//	alert(arr);
	arr[1][0]=1;
	arr[1][1]=2;
	arr[1][2]=3;
	
	arr[2][0]=["test","again"];
	arr[2][1]=["java","script"];
	arr[2][2]=["read","books"];
	
	alert(arr);
	alert(arr[2]);
	alert(arr[2][1][1]);
}

//2.join메서드:배열에 값들 사이에 문자열이 추가되어 텍스트로 반환된다.
function joinTest(){
	var fruitArray=['apple','peach','lemon','lime'];
	var resultString=fruitArray.join("-");
	alert(typeof resultString);
}

//sort()메서드:배열을 정렬-문자를 정렬할때 사용한다.(기본 사전식 정렬)
function sortTest01(){
	var arrayTest=['a','e','c','d','b'];
	arrayTest.sort();//정렬됨: ['a','b','c','d','e']
	alert(arrayTest);
}

//sort()메서드:숫자는 크기순으로 정렬하기 때문에 비교할 수 있는
//           함수를 만들어 줘야 한다.
function sortTest02(){
	var arrayTest=[4,2,75,3,6,34,5,65,22];
	//기능 자체를 전달해야 되기 때문에 함수 이름만 작성
	arrayTest.sort(compareNum);
	
	alert(arrayTest);
}

//숫자의 크기를 비교할 수 있는 함수 만들자!!
//리턴값이 양수이면 앞에 값이 크고, 음수이면 뒤에 값이 크다
function compareNum(a,b){
	return a-b;
}

function reverseTest(){
	var numArray=[4,2,6,85,12,47];
	numArray.sort(compareNum);
	numArray.reverse();
	alert(numArray);
}

//push():배열에 값을 저장하며, 자동으로 인덱스 길이가 증가
//shift():배열에서 첫번째요소의 값을 잘라내기해서 가져옴
//pop():배열에서 마지막 요소의 값을 잘라내기해서 가져옴
function pushAndShift(){
//	var queue=new Array();
	var queue=[];
	queue.push("first");
	queue.push("second");
	queue.push("third");
	alert(queue+":길이값 "+queue.length);
	
	var sVal=queue.shift();//첫번째요소 가져옴
	alert("가져온 값:"+sVal+", 기존배열:"+queue);
	
	var pVal=queue.pop();//마지막요소 가져옴
	alert("가져온 값:"+pVal+", 기존배열:"+queue);
}


function sliceTest(){
	var arrayTest=new Array(4);//길이도 정의
	arrayTest[0]=1;
	arrayTest[1]=2;
	arrayTest[2]=3;
	arrayTest[3]=4;
	
	//slice(1,3) 배열의 인덱스 1부터~2까지 잘라서 배열로 생성
	var arrayTest02=arrayTest.slice(1, 3);
	
	alert(arrayTest+":"+arrayTest02);
	
	arrayTest02[0]=7;
	alert(arrayTest+"  :  "+arrayTest02);
	
	//배열의 값이 객체라면..복사한쪽에서 값을 바꾸면 원본도 바뀐다.
	var arrayTestObj=new Array(4);//길이도 정의
	arrayTestObj[0]=[1,2];
	arrayTestObj[1]=[3,4];
	arrayTestObj[2]=[5,6];
	arrayTestObj[3]=[7,8];
	
	var arrayTestObj02=arrayTestObj.slice(1, 3);
	arrayTestObj02[0][0]=15;
	
	console.log(arrayTestObj+" : "+arrayTestObj02);
	
	/* java에서도 동일한 개념 mutable, immutable
	int a=5;
	int b=a; // a--->b로 5라는 값을 전달
	b=10;
	a=5
	
	int [] i={1,2,3,4}
	int [] j=i; // i--> j로 주소가 전달
	*/
}

function arraySearch(val){
	var searchArray=['a','b','c','d','b'];
	if(val=='first'){
		alert(searchArray.indexOf('b'));
	}
	if(val=='last'){
		alert(searchArray.lastIndexOf('b'));
	}
}

function concatTest(){
	var testArray=new Array();
	testArray.push(new Array("one","two"));
	testArray.push(new Array("three","four"));
	testArray.push(new Array("five","six"));
	
	var newArray=
		testArray[0].concat(testArray[1],testArray[2]);
	alert(newArray+",길이는:"+newArray.length);
}

//splice():배열의 요소를 삭제하거나 바꾸는 기능 제공
//인자값1개: 인자값의 인덱스부터 끝까지 삭제한다.
//인자값2개: 시작인덱스부터 제거할 요소의 개수를 설정
//인자값3개: 시작인덱스부터 길이 설정하고, 설정된 요소들을 다른 값으로 바꾼다
//         (시작인덱스,길이,바꿀값1,바꿀값2,....)
function spliceTest(){
	var testArray=["a","b","c","d","e","f"];
	//요소제거
	//인덱스 2부터 끝까지 제거
//	testArray.splice(2);
	//인덱스 1부터 길이 3까지 제거
//	testArray.splice(1, 3);
	//인덱스 1부터 길이 3까지 제거하고 다른 값("ab","bc")으로 대체하기
	testArray.splice(1, 3, "ab","bc");
//	alert(testArray);
	
	//배열에 특정값(여러개)을 다른 값으로 대체
	//문제:"ab"인 값들을 모두 "##"값으로 변경하세요
	//방법:"ab"라는 값을 검색해서 인덱스를 구하면, 위치를 알 수 있음
	//    반복문을 활용, indexOf() 활용, splice() 활용
	var testArray02=["ab","bc","ab","de","ab","ab","de","de"];
	
	//조건식: "ab"가 배열에 존재한다면!!
	while(testArray02.indexOf("ab")!=-1){
		testArray02.splice(testArray02.indexOf("ab"), 1, "##");		
	}
	
	alert(testArray02);
}












