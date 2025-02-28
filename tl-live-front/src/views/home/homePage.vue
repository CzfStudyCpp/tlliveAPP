<template>
    <hr />
    <el-row>
        <el-col :span="6">
            <el-statistic title="总活跃用户" :value="268500" />
        </el-col>
        <el-col :span="6">
            <el-statistic :value="138">
                <template #title>
                    <div style="display: inline-flex; align-items: center">
                        用户男女比例
                        <el-icon style="margin-left: 4px" :size="12">
                            <Male />
                        </el-icon>
                    </div>
                </template>
                <template #suffix>/100</template>
            </el-statistic>
        </el-col>
        <el-col :span="6">
            <el-statistic title="总交易数" :value="outputValue" />
        </el-col>
        <el-col :span="6">
            <el-statistic title="客户回馈数" :value="562">
                <template #suffix>
                    <el-icon style="vertical-align: -0.125em">
                        <ChatLineRound />
                    </el-icon>
                </template>
            </el-statistic>
        </el-col>
    </el-row>
    <hr />
    <el-row :gutter="16">
        <el-col :span="8">
            <div class="statistic-card">
                <el-statistic :value="98500">
                    <template #title>
                        <div style="display: inline-flex; align-items: center">
                            日活用户
                            <el-tooltip effect="dark" content="Number of users who logged into the product in one day"
                                placement="top">
                                <el-icon style="margin-left: 4px" :size="12">
                                    <Warning />
                                </el-icon>
                            </el-tooltip>
                        </div>
                    </template>
                </el-statistic>
                <div class="statistic-footer">
                    <div class="footer-item">
                        <span>昨日同比</span>
                        <span class="green">
                            24%
                            <el-icon>
                                <CaretTop />
                            </el-icon>
                        </span>
                    </div>
                </div>
            </div>
        </el-col>
        <el-col :span="8">
            <div class="statistic-card">
                <el-statistic :value="693700">
                    <template #title>
                        <div style="display: inline-flex; align-items: center">
                           月活跃用户
                            <el-tooltip effect="dark" content="Number of users who logged into the product in one month"
                                placement="top">
                                <el-icon style="margin-left: 4px" :size="12">
                                    <Warning />
                                </el-icon>
                            </el-tooltip>
                        </div>
                    </template>
                </el-statistic>
                <div class="statistic-footer">
                    <div class="footer-item">
                        <span>上月环比</span>
                        <span class="red">
                            12%
                            <el-icon>
                                <CaretBottom />
                            </el-icon>
                        </span>
                    </div>
                </div>
            </div>
        </el-col>
        <el-col :span="8">
            <div class="statistic-card">
                <el-statistic :value="72000" title="New transactions today">
                    <template #title>
                        <div style="display: inline-flex; align-items: center">
                            今日新增交易数
                        </div>
                    </template>
                </el-statistic>
                <div class="statistic-footer">
                    <div class="footer-item">
                        <span>相比昨日</span>
                        <span class="green">
                            16%
                            <el-icon>
                                <CaretTop />
                            </el-icon>
                        </span>
                    </div>
                    <div class="footer-item">
                        <el-icon :size="14">
                            <ArrowRight />
                        </el-icon>
                    </div>
                </div>
            </div>
        </el-col>
    </el-row>
    <hr/>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useTransition } from '@vueuse/core'
import { ChatLineRound, Male } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import {
    ArrowRight,
    CaretBottom,
    CaretTop,
    Warning,
} from '@element-plus/icons-vue'

ElMessage({
    showClose: true,
    message: '欢迎进入图乐直播系统',
    type: 'success',
})

const source = ref(0)
const outputValue = useTransition(source, {
    duration: 1500,
})
source.value = 172000
</script>

<style scoped>
.el-col {
    text-align: center;
}

:global(h2#card-usage ~ .example .example-showcase) {
    background-color: var(--el-fill-color) !important;
}

.el-statistic {
    --el-statistic-content-font-size: 28px;
}

.statistic-card {
    height: 100%;
    padding: 20px;
    border-radius: 4px;
    background-color: var(--el-bg-color-overlay);
}

.statistic-footer {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    font-size: 12px;
    color: var(--el-text-color-regular);
    margin-top: 16px;
}

.statistic-footer .footer-item {
    display: flex;
    justify-content: center;
    align-items: center;
}

.statistic-footer .footer-item span:last-child {
    display: inline-flex;
    align-items: center;
    margin-left: 4px;
}

.green {
    color: var(--el-color-success);
}

.red {
    color: var(--el-color-error);
}
</style>