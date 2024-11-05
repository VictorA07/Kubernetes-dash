def call(){
    def shouldExecuteStage(stageName) {
        return params.STAGE == 'all' || params.STAGE == stageName
    }
}