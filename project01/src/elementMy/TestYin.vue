
<template>
  <el-popover placement="top" width="160" v-model="visible">
    <p>这是一段内容这是一段内容确定删除吗？</p>
    <div style="text-align: right; margin: 0">
      <el-button size="mini" type="text" @click="visible = false">取消</el-button>
      <el-button type="primary" size="mini" @click="visible = false" id="app"> 确定</el-button>
    </div>
    <el-button id="test" slot="reference" @click="showDialog()">删除</el-button>
  </el-popover>

  
</template>

<script>
document.getElementById("app").onclick=function()
  {
    console.log("test")
  }
export default {
  data() {
    return {
      visible: false,
    };
  }
}
</script>