<template>
    <div>
        <div>
            <video class="videoT" src="@/assets/hand_ready.mp4" autoplay loop muted id="music"></video>
        </div>
        <div class="mylog">
            <img src="@/assets/logoB.png" style="width:100px;  height: 100px;">
        </div>

        <div>
            <h2 style="color:#76777a; " class="boxTxt">{{ this.headerTxt }}</h2>
            <!-- 文本区域 -->
            <p style="color: aliceblue; font-size: 25px;text-indent:50px ; position:absolute ;top: 50%;">
                欢迎来到消费管理系统，这是一个专为帮助个人统计和管理消费而设计的网页应用。
                我们的目标是帮助用户了解自己的消费习惯，优化支出，做到理性消费。无论你是希望攒钱，还是想更好地管理财务，消费管理系统都是你不可或缺的工具。
                通过科学的消费管理，你将能够实现理性的消费习惯，达到财务自由的目标。

                加入我们，开启你的理性消费之旅吧！
            </p>
        </div>

        <a class="buttonMy">
            <el-button type="info" plain @click="changePage"
                style="position: absolute; bottom: 200px;">开启理想消费之旅</el-button>
        </a>


        <div class="foldmy">
            <i class="el-icon-s-fold" @click="drawer = true" type="primary" style="margin-left: 16px;"></i>
        </div>
        <el-drawer :visible.sync="drawer" :direction="direction" :before-close="handleClose">
            <div class="routerArrange">
                <router-link to="/">首页</router-link>
                <br>
                <router-link to="/login">用户登录</router-link>
                <br>
                <router-link to="/emp">功能页面</router-link>
            </div>

        </el-drawer>
    </div>
</template>

<script>
export default {
    data() {
        return {
            drawer: false,
            headerTxt: "",
            headerw: ["欢迎来到消费管理系统"],
            indexa: 0,
            headerW: new Array(11),
            typeingInterval: null,
            currentIndex: 0,
            direction: 'rtl',
        };
    },
    methods: {
        changePage() {
            // window.location.href = "http://localhost:8000/#/emp";
            this.$router.push('/emp');
        },
        handleClose(done) {
            this.$confirm('确认关闭,您还未选择功能？')
                .then(_ => {
                    done();
                })
                .catch(_ => { });
        },

        typeLine() {
            console.log(this.headerw[0].length)
            if (this.currentIndex >= this.headerw[0].length) {clearInterval(this.typeingInterval);  setTimeout(this.loadWords,3000)}
            else {
                if (this.headerTxt == null) { this.headerTxt = this.headerW[0][this.currentIndex]; this.currentIndex++; } else {
                    this.headerTxt += this.headerw[0][this.currentIndex];
                    this.currentIndex++;
                }
            }
           
        },
        loadWords() {

            this.currentIndex=0;
            this.headerTxt="";
            if (this.typeingInterval) {
                clearInterval(this.typeingInterval);
            }
            this.typeingInterval = setInterval(this.typeLine, 200)

        }
    },
    mounted() {

        this.loadWords()

    }
};

</script>

<style>
.routerArrange {
    display: flex;
    flex-direction: column;
    justify-self: flex-start;
}

.routerArrange a {
    background-color: black;
    text-decoration: none;
    color: #EBEEF5;
    border: 12px solid black;
}


.routerArrange a.router-link-exact-active {
    text-decoration: none;
    background-color: rgb(1, 101, 101);
    border: 12px solid rgb(1, 101, 101);

}

.buttonMy {
    display: flex;
    flex-direction: row;
    justify-content: center;
}

.boxTxt {
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    margin: auto;
    bottom: 50%;
    height: 150px;
    width: auto;
    min-height: 150px;

    max-height: 300px;
    text-align: center;
    font-size: 50px;

}


.mylog {
    position: absolute;
    left: 2%;
}

.el-icon-s-fold {
    font-size: 30px;
    color: #EBEEF5;


}

.videoT {
    position: absolute;
    top: 10%;
    left: 0;
    min-width: 100%;
    height: auto;
    width: auto;
    object-fit: cover;
    -webkit-filter: grayscale(40%);
}

.foldmy {
    position: absolute;
    right: 2%;
    top: 12%;
    margin: auto;
}

.el-drawer {
    max-width: 150px;
    min-width: 10px;
}
</style>