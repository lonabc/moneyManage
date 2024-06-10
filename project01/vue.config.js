const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  // publicPath : './', //注意，可能导致出错
  lintOnSave: false,

  transpileDependencies: [
    'quasar'
  ],

  devServer:{
    host: '0.0.0.0',
    port:8000,
    client:{
      overlay:false
    },
    open:true ,
  },

  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: true
    }
  }
})

