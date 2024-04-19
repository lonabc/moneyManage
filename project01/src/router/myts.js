// export class MyCustomError extends Error {
//     constructor(message: string) {
//       super(message); // 调用父类的构造函数
//       this.name = 'MyCustomError'; // 设置错误名称
//     }
//   }
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var msg = 'hello ts!'; //定义一个类型为String的msg的变量
var isTrue = false;
var age = 20;
console.log(msg);
console.log(isTrue);
console.log(age);
var m2 = function (name) {
    return name.toLowerCase() + msg;
};
function printText(s, teststring) {
    console.log(s, teststring);
}
printText('hello', 1);
var c1 = { name: '小白', age: 1 }; //定义变量为Cat类型
var c2 = { name: '小明' }; //错误，缺少age属性,但是只要在属性名上加上?，表示该属性可选，就正确了
var User = /** @class */ (function () {
    function User(name) {
        this.name = name;
    }
    User.prototype.study = function () {
        console.log(this.name + '正在学习');
    };
    User.prototype.eat = function () {
        console.log(this.name + 'eat');
    };
    return User;
}());
var Son = /** @class */ (function (_super) {
    __extends(Son, _super);
    function Son() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    Son.prototype.say = function () {
        console.log(this.name + 'say English');
    };
    return Son;
}(User));
var myson = new Son('why');
myson.eat();
myson.say();
myson.study();
var user = new User('张三'); //创建User对象
console.log(user.name); //输出类中的属性
user.study(); //调用类中的方法
user.eat();
