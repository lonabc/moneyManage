// export class MyCustomError extends Error {
//     constructor(message: string) {
//       super(message); // 调用父类的构造函数
//       this.name = 'MyCustomError'; // 设置错误名称
//     }
//   }

  let msg:string='hello ts!' ;//定义一个类型为String的msg的变量
  let isTrue:boolean=false ;
  let age:number=20;
  
  console.log(msg);
  console.log(isTrue);
  console.log(age);

  const m2=(name:string):string =>  //name为String类型，同时该函数的返回值为String
  {
     return name.toLowerCase()+msg;
  }

  function printText(s:string,teststring:1 | 2 | 3)
  {
    console.log(s,teststring);
  }
  printText('hello',1);


  interface Cat{//定义一个接口，名字为cat，此处接口只在编译时起作用，用于约束变量赋值
    name:string,
    age ?:number
  }

  const c1: Cat={name:'小白',age:1}//定义变量为Cat类型
  const c2: Cat={name:'小明'}//错误，缺少age属性,但是只要在属性名上加上?，表示该属性可选，就正确了
 
  interface Animal{
    name: string ;
    eat():void
  }

 class User implements Animal{
    name:string;//定义类中的属性
    constructor(name:string)//构造方法
    {
      this.name=name
    }
    study()
    {
      console.log(this.name+'正在学习');
    }
    eat():void {
         console.log(this.name+'eat');
    }

 }
class Son extends User{
   say():void {
      console.log(this.name+'say English')
   }
}
 const myson=new Son('why')
 myson.eat();
 myson.say();
 myson.study();


 const user =new User('张三');//创建User对象
 console.log(user.name);//输出类中的属性
 user.study();//调用类中的方法
 user.eat();

