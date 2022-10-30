<template>
  <view class="content">
    <view>
      <text class="title">{{ title }}</text>
    </view>
    <view class="image-canvas">
      <image class="image-view" mode="aspectFit" :src="src"></image>
      <view>
        <text>图片：{{ fileName }}</text>
      </view>
      <view>
        <text>人脸数：{{ faceNumber }}</text>
      </view>
      <button type="primary" plain="true" @click="chooseImage" style="width: 100%;margin-top: 10px">choose image
      </button>
      <button type="primary" plain="true" @click="clear" style="width: 100%;margin-top: 10px">clear</button>
    </view>
    <loading></loading>
  </view>
</template>

<script>
import loading from '../components/loading';

export default {
  components: {
    loading
  },
  data() {
    return {
      title: 'Hello',
      src: '/static/image.png',
      fileName: '',
      faceNumber: 0
    }
  },
  methods: {
    chooseImage() {
      const vm = this;
      uni.chooseImage({
        count: 1,
        sizeType: ['original'],
        sourceType: ['album', 'camera'],
        success: function (res) {
          vm.$set(vm, 'src', res.tempFilePaths[0]);
          vm.uploadImage(res.tempFilePaths[0]);
          vm.fileName = res.tempFiles[0].name;
          vm.faceNumber = 0;
        }
      });
    },
    uploadImage(url) {
      this.$showLoading();
      uni.uploadFile({
        url: 'http://192.168.3.26:8092/face/recognition', //仅为示例，非真实的接口地址
        filePath: url,
        name: 'file',
        success: (uploadFileRes) => {
          const resultData = JSON.parse(uploadFileRes.data);
          if (resultData.code === 200) {
            const data = resultData.data;
            this.faceNumber = data.number;
          }
        },
        complete: () => {
          this.$hideLoading();
        }
      });
    },
    clear() {
      this.src = '/static/image.png';
      this.fileName = '';
      this.faceNumber = 0;
    }
  }
}
</script>

<style>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.title {
  font-size: 36rpx;
  color: #8f8f94;
}

.image-canvas {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.image-view {
  margin-bottom: 10px;
}
</style>
