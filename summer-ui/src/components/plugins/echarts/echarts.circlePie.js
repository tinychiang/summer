const pie = {
  tooltip: {
    trigger: 'item',
  },
  series: [
    {
      name: '科创板 T5',
      type: 'pie',
      radius: ['60%', '95%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2,
      },
      label: {
        show: false,
        position: 'center',
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '20',
          fontWeight: 'bold',
        },
      },
      labelLine: {
        show: false,
      },
      data: [
        { value: 20.01, name: '正帆科技' },
        { value: 20.01, name: '九号公司-WD' },
        { value: 20, name: '键凯科技' },
        { value: 20, name: '金博股份' },
        { value: 19.71, name: '华峰测控' },
      ],
    },
  ],
};
export default pie;
