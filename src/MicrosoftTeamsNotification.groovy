import com.dtolabs.rundeck.plugins.notification.NotificationPlugin
import groovy.json.JsonOutput

rundeckPlugin(NotificationPlugin){
    title="Microsoft Teams notification Plugin"
    description="Allows to set up notification for Microsoft Teams chats for a channel, via Webhook URL. To use it you will have to obtain webhook for your channel first and setit up."

    configuration{
        webhook_url title:"Webhook URL", required: true, type:"String", description:"You may find it in Microsoft Teams Channel user interfaces by using Incomming Webhook connector via:  Channel Name -> Connectors -> Incomming Webhook"
    }

    onstart {
        type = "START"
        color = "696969"
        //Single argument, the configuration properties are available automatically
// sections:         "activityImage": "https://sample.com/image.png",
        json_payload = JsonOutput.toJson([
            "@type": "MessageCard",
            "@context": "http://schema.org/extensions",
            "themeColor": "${color}",
            "summary": "${execution.project}:${execution.job.group}:${execution.job.name}",
            "sections": [[
                "activityTitle": "${execution.job.name}",
                "activitySubtitle": "${execution.project}:${execution.job.group}",
                "activityImage": "https://www.samplecom/image.png",
                "facts": [[
                    "name": "Job ID",
                    "value": "#${execution.job.id}"
                ], [
                    "name": "Execution ID",
                    "value": "#${execution.id}"
                ], [
                    "name": "Execution Status",
                    "value": "#${execution.status}"
                ], [
                    "name": "Options",
                    "value": "${execution.context.option}"
                ], [
                    "name": "Started",
                    "value": "${execution.dateStarted}"
                ], [
                    "name": "Ended",
                    "value": "${execution.dateEnded}"
                ]],
                "markdown": true
            ]],
            "potentialAction": [[
                "@type": "OpenUri",
                "name": "View in Rundeck",
                "targets": [[
                    "os": "default",
                    "uri": "${execution.href}"
                ]]
            ]]
        ])
        process = [ 'bash', '-c', "curl -v -k -X POST -H \"Content-Type: application/json\" -d '${json_payload}' ${configuration.webhook_url}" ].execute().text

        return true
    }

// sections:         "activityImage": "https://sample.com/image.png",
    onfailure {
        type = "FAILURE"
        color = "E81123"
        //with no args, there is a "configuration" and an "execution" variable in the context
        json_payload = JsonOutput.toJson([
            "@type": "MessageCard",
            "@context": "http://schema.org/extensions",
            "themeColor": "${color}",
            "summary": "${execution.project}:${execution.job.group}:${execution.job.name}",
            "sections": [[
                "activityTitle": "${execution.job.name}",
                "activitySubtitle": "${execution.project}:${execution.job.group}",
                "activityImage": "https://www.samplecom/image.png",
                "facts": [[
                    "name": "Job ID",
                    "value": "#${execution.job.id}"
                ], [
                    "name": "Execution ID",
                    "value": "#${execution.id}"
                ], [
                    "name": "Execution Status",
                    "value": "#${execution.status}"
                ], [
                    "name": "Options",
                    "value": "${execution.context.option}"
                ], [
                    "name": "Started",
                    "value": "${execution.dateStarted}"
                ], [
                    "name": "Ended",
                    "value": "${execution.dateEnded}"
                ]],
                "markdown": true
            ]],
            "potentialAction": [[
                "@type": "OpenUri",
                "name": "View in Rundeck",
                "targets": [[
                    "os": "default",
                    "uri": "${execution.href}"
                ]]
            ]]
        ])
        process = [ 'bash', '-c', "curl -v -k -X POST -H \"Content-Type: application/json\" -d '${json_payload}' ${configuration.webhook_url}" ].execute().text

        return true
    }

    // sections:         "activityImage": "https://sample.com/image.png",
    onsuccess {
        type = "SUCCESS"
        color = "228B22"
        //with no args, there is a "configuration" and an "execution" variable in the context
        json_payload = JsonOutput.toJson([
            "@type": "MessageCard",
            "@context": "http://schema.org/extensions",
            "themeColor": "${color}",
            "summary": "${execution.project}:${execution.job.group}:${execution.job.name}",
            "sections": [[
                "activityTitle": "${execution.job.name}",
                "activitySubtitle": "${execution.project}:${execution.job.group}",
                "activityImage": "https://www.samplecom/image.png",
                "facts": [[
                    "name": "Job ID",
                    "value": "#${execution.job.id}"
                ], [
                    "name": "Execution ID",
                    "value": "#${execution.id}"
                ], [
                    "name": "Execution Status",
                    "value": "#${execution.status}"
                ], [
                    "name": "Options",
                    "value": "${execution.context.option}"
                ], [
                    "name": "Started",
                    "value": "${execution.dateStarted}"
                ], [
                    "name": "Ended",
                    "value": "${execution.dateEnded}"
                ]],
                "markdown": true
            ]],
            "potentialAction": [[
                "@type": "OpenUri",
                "name": "View in Rundeck",
                "targets": [[
                    "os": "default",
                    "uri": "${execution.href}"
                ]]
            ]]
        ])
        process = [ 'bash', '-c', "curl -v -k -X POST -H \"Content-Type: application/json\" -d '${json_payload}' ${configuration.webhook_url}" ].execute().text

        return true
    }
}
